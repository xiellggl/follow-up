package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/followup/role")
public class RoleController {
    @Resource
    UserComponent userComponent;
    @Resource
    RoleService roleService;

    /**
     * 保存
     */
    @RequestMapping("/add/save")
    public BizResult addSave(HttpServletRequest request, Role role) {
        String[] permissionIds = request.getParameterValues("permissionId");
        if (permissionIds == null || permissionIds.length == 0) {
            return BizResult.fail("保存角色前，请选择一个模块");
        }
        return roleService.addRole(role, permissionIds) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 保存
     */
    @RequestMapping("/edit/save")
    public BizResult editSave(HttpServletRequest request, Role role) {
        String[] permissionIds = request.getParameterValues("permissionId");
        if (permissionIds == null || permissionIds.length == 0) {
            return BizResult.fail("保存角色前，请选择一个模块");
        }
        return roleService.updateRole(role, permissionIds) ? BizResult.SUCCESS : BizResult.FAIL;

    }

}
