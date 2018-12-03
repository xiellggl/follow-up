package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.service.RoleService;
import com.dayi.follow.vo.PermissionVo;
import com.dayi.mybatis.support.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    UserComponent userComponent;
    @Resource
    RoleService roleService;
    @Resource
    PermissionService permissionService;


    /**
     * 查询功能
     */
    @RequestMapping("/query")
    public BizResult<Page<Permission>> bindQuery(Page<Permission> page, PermissionVo permissionVo) {
        return BizResult.succ(permissionService.searchPermissions(page, permissionVo));
    }

    /**
     * 功能编辑提交
     */
    @RequestMapping("/add/save")
    public BizResult panelSave(Permission permission) {
        return permissionService.addPermission(permission) ? BizResult.SUCCESS : BizResult.FAIL;
    }
    /**
     * 绑定功能提交
     */
    @RequestMapping("/bind/save")
    public BizResult bindSave(HttpServletRequest request) {
        String moduleId = request.getParameter("moduleId");
        String permissionIds = request.getParameter("id");
        permissionService.bindModule(moduleId, permissionIds);
        return BizResult.SUCCESS;
    }

}
