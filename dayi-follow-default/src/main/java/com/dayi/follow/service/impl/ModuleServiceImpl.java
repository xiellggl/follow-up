package com.dayi.follow.service.impl;


import com.dayi.common.util.Misc;
import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.dao.follow.ModuleMapper;
import com.dayi.follow.dao.follow.PermissionMapper;
import com.dayi.follow.model.follow.Menu;
import com.dayi.follow.model.follow.Module;
import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.service.ModuleService;
import com.dayi.follow.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 跟进人 业务实现类
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public List<Menu> queryMenus(String rootId, boolean isShowDeleteModulePermission, boolean isShowModuleForNoPermission, Module module, PermissionVo permission) {
        //附上正常条件
        if (!isShowDeleteModulePermission) {
            module.setDelStatus(Module.DEL_STATUS_NO.id);
            permission.setDelStatus(Permission.DEL_STATUS_NO.id);
        }
        //所有模块
        List<Module> rootModule = findAllModules(module);
        if (rootModule == null || rootModule.isEmpty()) {
            return Collections.emptyList();
        }
        //菜单最后的结果
        List<Menu> menuList = new ArrayList<>();
        //所有权限
        List<Permission> rootPermission = findAllPermissions(permission);
        //根据需要，过滤未绑定的权限
        if (permission != null && permission.getBindStatus() != null) {
            if (PermissionVo.BIND_STATUS_NORMAL.id == permission.getBindStatus()) {
                Iterator<Permission> it = rootPermission.iterator();
                while (it.hasNext()) {
                    Permission p = it.next();
                    if (Misc.isEmpty(p.getModuleid()) || "0".equals(p.getModuleid())) {
                        it.remove();
                    }
                }
            }
        }

        //模块转菜单数据
        List<Menu> rootMenu = modulesToConvertMenu(rootModule);
        //权限转菜单数据
        List<Menu> rootMenu2 = permissionsToConvertMenu(rootPermission);
        //合并两个菜单数据作为"原始的数据"
        rootMenu.addAll(rootMenu2);

        //先找到所有的起始菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            //指定起始节点,未指定则从parentId=0或空开始
            if (rootId != null) {
                String id = rootMenu.get(i).getParentId();
                if (id != null && id.equals(rootId)) {
                    menuList.add(rootMenu.get(i));
                }
            } else {
                if (Misc.isEmpty(rootMenu.get(i).getParentId()) || "0".equals(rootMenu.get(i).getParentId())) {
                    menuList.add(rootMenu.get(i));
                }
            }
        }
        // 为起始菜单设置子菜单，getChild是递归调用的
        eachMenu(menuList, rootMenu, isShowModuleForNoPermission);
        Collections.sort(menuList);
        return menuList;
    }

    /**
     * 获取所有模块
     *
     * @param module
     * @return
     */
    @Override
    public List<Module> findAllModules(Module module) {
        return moduleMapper.findList(module);
    }

    /**
     * 获取所有权限
     *
     * @param permissionVo
     * @return
     */
    @Override
    public List<Permission> findAllPermissions(PermissionVo permissionVo) {
        return permissionMapper.findList(permissionVo);
    }

    /***
     * 模块转换为菜单
     *
     * @param modules
     * @return
     */
    public List<Menu> modulesToConvertMenu(List<Module> modules) {
        List<Menu> menus = new ArrayList<>();
        for (Module module : modules) {
            Menu menu = new Menu(module.getId(), module.getName(), module.getParentid(), module.getUrl(), module.getStatus(), module.getSort());
            menu.setType(Menu.TYPE_NONLEAF_NODE.id);
            menus.add(menu);
        }
        return menus;
    }

    /***
     * 权限转换为菜单
     *
     * @param permissions
     * @return
     */
    public List<Menu> permissionsToConvertMenu(List<Permission> permissions) {
        List<Menu> menus = new ArrayList<>();
        for (Permission permission : permissions) {
            Menu menu = new Menu(permission.getId(), permission.getName(), permission.getModuleid(), permission.getUrl(), permission.getDisplayStatus(), permission.getSort());
            menu.setType(Menu.TYPE_LEAF_NODE.id);
            menus.add(menu);
        }
        return menus;
    }

    /**
     * 递归处理
     *
     * @param menuList
     * @param rootMenu
     * @param isShowModuleForNoPermission
     */
    public void eachMenu(List<Menu> menuList, List<Menu> rootMenu, boolean isShowModuleForNoPermission) {
        if (isShowModuleForNoPermission) {
            for (Menu menu : menuList) {
                List<Menu> childs = getChild(menu, rootMenu, isShowModuleForNoPermission);
                menu.setChildMenus(childs);
            }
        } else {
            Iterator<Menu> iter = menuList.iterator();
            while (iter.hasNext()) {
                Menu menu = iter.next();
                List<Menu> childs = getChild(menu, rootMenu, isShowModuleForNoPermission);
                //没有权限的模块删除掉
                if (Menu.TYPE_NONLEAF_NODE.id == menu.getType() && childs == null) {
                    iter.remove();
                } else {
                    menu.setChildMenus(childs);
                }
            }
        }
    }

    /**
     * 递归查找子菜单
     *
     * @param currentMenu                 当前菜单
     * @param rootMenu                    要查找的列表
     * @param isShowModuleForNoPermission
     * @return
     */
    private List<Menu> getChild(Menu currentMenu, List<Menu> rootMenu, boolean isShowModuleForNoPermission) {
        //递归退出条件：叶子节点退出
        if (Menu.TYPE_LEAF_NODE.id == currentMenu.getType()) {
            return null;
        }
        // 找子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (!Misc.isEmpty(menu.getParentId())) {
                if (menu.getParentId().equals(menu.getId())) {
                    throw new RuntimeException("递归数据异常，id与父id一样!" + menu.getParentId() + "=" + menu.getId());
                }
                if (menu.getParentId().equals(currentMenu.getId())) {
                    childList.add(menu);
                }
            }
        }
        //把子菜单的子菜单再循环一遍,并递归
        eachMenu(childList, rootMenu, isShowModuleForNoPermission);
        Collections.sort(childList);
        //递归退出条件：无子菜单
        if (childList.isEmpty()) {
            return null;
        }
        return childList;
    }

    @Override
    public Module getModule(String id) {
        return moduleMapper.get(id);
    }


    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.ADD, what = "模块管理", note = "添加模块",authorName = "")
    public boolean addModule(Module module) {
        if (module.getParentid() == null) {
            module.setParentid("");
        }
        module.setUpdateTime(new Date());
        module.setId(moduleMapper.getNewId());
        module.setDelStatus(Module.DEL_STATUS_NO.id);
        return 1 == moduleMapper.add(module);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "模块管理", note = "更新模块")
    public boolean updateModule(Module module) {
        if (module.getParentid() == null) {
            module.setParentid("");
        }
        module.setUpdateTime(new Date());
        return 1 == moduleMapper.update(module);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.DELETE, what = "模块管理", note = "模块删除")
    public boolean deleteModule(String id) {
        Module module = getModule(id);
        if (null != module) {
            Module m = new Module();
            m.setId(id);
            m.setUpdateTime(new Date());
            m.setDelStatus(Module.DEL_STATUS_IS.id);
            return 1 == moduleMapper.update(m);
        }
        return false;
    }

    /**
     * "剪叶子"算法实现树形结构的搜索功能
     */
    @Override
    public List<Menu> eachMenu(List<Menu> menus, String keyword) {
        //退出条件
        if (menus == null || menus.isEmpty()) {
            return Collections.emptyList();
        }
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            if (menu.getChildMenus() == null || menu.getChildMenus().isEmpty()) {
                if (StringUtils.isNotBlank(keyword)) {
                    String name = Misc.toString(menu.getName());
                    String url = Misc.toString(menu.getUrl());
                    if (name.indexOf(keyword) == -1 && url.indexOf(keyword) == -1) {
                        iterator.remove();
                    }
                }
            }
            eachMenu(menu.getChildMenus(), keyword);
        }
        return menus;
    }



}

