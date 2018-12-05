package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.Misc;
import com.dayi.follow.model.follow.Module;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.ModuleService;
import com.dayi.follow.model.follow.Menu;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.vo.PermissionVo;
import com.dayi.follow.vo.sys.ModuleSearchVo;
import com.dayi.mybatis.support.Page;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import com.dayi.user.authorization.authc.AuthenticationInfo;
import com.dayi.user.authorization.authz.AuthorizationInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/module")
public class ModuleController {

    @Resource
    ModuleService moduleService;

    /**
     * 加载菜单
     * @return
     */
    @RequestMapping("/menus")
    @ResponseBody
    public BizResult menus(HttpServletRequest request) {
        // 获取权限
        List<AuthorizationInfo> authorizationInfos = AuthorizationManager.getAuthorizationInfos(request);
        if (null == authorizationInfos) {
            return BizResult.fail("请先登录");
        }
        List<String> permissionIds = new ArrayList<>(5);
        for (AuthorizationInfo authorizationInfo : authorizationInfos) {
            permissionIds.addAll(authorizationInfo.getPermissions());
        }

        PermissionVo permissionVo =  new PermissionVo();
        permissionVo.setPermissions(permissionIds);
        List<Menu> menus = moduleService.queryMenus(permissionVo);
        return BizResult.succ(menus);
    }


    /**
     * 加载所有模块权限
     * @return
     */
    @RequestMapping("/listAll")
    @ResponseBody
    public BizResult listAll(Boolean isOnlyShowEnable) {
        List<Menu> menus = moduleService.listAll(isOnlyShowEnable);
        return BizResult.succ(menus);
    }

    /**
     * 新增模块
     * @param request
     * @param module
     * @return
     */
    @RequestMapping("/add/save")
    @ResponseBody
    public BizResult addSave(HttpServletRequest request, Module module) {
        // 获取当前用户信息，设置操作人
        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        if (null == accountInfo) {
            return BizResult.fail("请先登录.");
        }
        module.setCreateBy(accountInfo.getUserName());

        return moduleService.addModule(module) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 编辑模块
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BizResult edit(String id) {
        if (Misc.isEmpty(id)) {
            return BizResult.fail("请选择要编辑的模块.");
        }
        return BizResult.succ(moduleService.getModule(id));
    }

    /**
     * 编辑保存模块
     * @param request
     * @param module
     * @return
     */
    @RequestMapping("/edit/save")
    @ResponseBody
    public BizResult save(HttpServletRequest request, Module module) {
        // 获取当前用户信息，设置操作人
        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        if (null == accountInfo) {
            return BizResult.fail("请先登录.");
        }
        module.setUpdateBy(accountInfo.getUserName());

        return moduleService.updateModule(module) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 启用禁用模块
     * @param id
     * @param enable
     * @return
     */
    @RequestMapping("/enableModule")
    @ResponseBody
    public BizResult enableModule(String id, boolean enable) {
        if (Misc.isEmpty(id)) {
            return BizResult.fail("请选择要禁用/启用的模块");
        }
        return moduleService.updateStatus(id, enable) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 删除模块
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BizResult delete(HttpServletRequest request) {
        String ids = request.getParameter("id");
        if (!Misc.isEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                if (!moduleService.deleteModule(id)) {
                    return BizResult.FAIL;
                }
            }
        }
        return BizResult.SUCCESS;
    }

    /**
     * 分页查询模块列表
     * @param moduleSearchVo
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(ModuleSearchVo moduleSearchVo, Model model) {
        Page<Module> page = moduleService.searchModule(moduleSearchVo);
        model.addAttribute("page", page);
        return "sys/module_list";
    }

    /*@RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        String keyword = request.getParameter("keyword");
        List<Menu> menus = moduleService.queryMenus(null, false, true, new Module(), new PermissionVo());
        if (StringUtils.isNotBlank(keyword)) {
            for (int i = 0; i < 6; i++) {
                moduleService.eachMenu(menus, keyword);
            }
        }
        model.addAttribute("menus",menus);
        return "sys/module_list";
    }*/
}
