package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Module;
import com.dayi.follow.model.follow.Menu;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.vo.PermissionVo;

import java.util.List;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface ModuleService {

    /**
     * 根据给定起始节点、模块筛选、权限筛选数据来查找菜单
     *
     * @param permission 权限筛选
     * @return
     */
    List<Menu> queryMenus(PermissionVo permission);

    /**
     * 查询所有模块、菜单
     *
     * @param isOnlyShowEnable 是否显示只显示启用菜单
     * @return
     */
    List<Menu> listAll(Boolean isOnlyShowEnable);

    /**
     * 查询所有模块（不包括菜单）
     *
     * @return
     */
    List<Menu> listModule();

    /**
     * 返回所有权限
     *
     * @param permissionVo
     * @return
     */
    List<Permission> findAllPermissions(PermissionVo permissionVo);

    /**
     * 根据id查询模块
     *
     * @param id
     * @return
     */
    Module getModule(String id);

    /**
     * 添加模块
     *
     * @param module
     * @return
     */
    boolean addModule(Module module);

    /**
     * 更新模块
     *
     * @param module
     * @return
     */
    BizResult updateModule(Module module);

    /**
     * 启用/禁用模块
     * @param id
     * @param enable
     * @return
     */
    BizResult updateStatus(String id, boolean enable);

    /**
     * 解绑功能
     * @param moduleId
     * @return
     */
    BizResult untying(String moduleId);

    /**
     * 删除角模块信息
     * @param id
     * @return
     */
    boolean deleteModule(String id);

    /**
     * "剪叶子"算法实现树形结构的搜索功能
     * @param menus
     * @param keyword
     * @return
     */
    List<Menu> eachMenu(List<Menu> menus, String keyword);
}
