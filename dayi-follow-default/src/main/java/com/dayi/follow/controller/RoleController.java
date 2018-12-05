package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.Misc;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.RoleService;
import com.dayi.mybatis.support.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    RoleService roleService;

    /**
     * 加载所有角色
     * @return
     */
    @RequestMapping("/listAll")
    @ResponseBody
    public BizResult listAll(Integer status) {
        List<Role> roles = roleService.listAll(status);
        return BizResult.succ(roles);
    }

    /**
     * 添加角色
     * @param request
     * @param role
     * @return
     */
    @RequestMapping("/add/save")
    @ResponseBody
    public BizResult addSave(HttpServletRequest request, Role role) {
        String[] permissionIds = request.getParameterValues("permissionId");
        if (permissionIds == null || permissionIds.length == 0) {
            return BizResult.fail("保存角色前，请选择一个模块");
        }
        return roleService.addRole(role, permissionIds) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 编辑角色
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BizResult edit(String id) {
        if (Misc.isEmpty(id)) {
            return BizResult.fail("请选择要编辑的角色.");
        }
        return BizResult.succ(roleService.getById(id));
    }

    /**
     * 编辑保存角色
     * @param request
     * @param role
     * @return
     */
    @RequestMapping("/edit/save")
    @ResponseBody
    public BizResult editSave(HttpServletRequest request, Role role) {
        String[] permissionIds = request.getParameterValues("permissionId");
        if (permissionIds == null || permissionIds.length == 0) {
            return BizResult.fail("保存角色前，请选择一个模块");
        }
        return roleService.updateRole(role, permissionIds) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 启用禁用角色
     * @param id
     * @param enable
     * @return
     */
    @RequestMapping("/enableRole")
    @ResponseBody
    public BizResult enableRole(String id, boolean enable) {
        if (Misc.isEmpty(id)) {
            return BizResult.fail("请选择要禁用/启用的角色");
        }
        return roleService.updateStatus(id, enable) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 删除角色
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
                if (!roleService.delete(id)) {
                    return BizResult.FAIL;
                }
            }
        }
        return BizResult.SUCCESS;
    }

    /**
     * 列表查询
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                   @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, Model model) {
        Page<Role> page = roleService.searchRole(pageNo, pageSize);
        model.addAttribute("page", page);
        return "sys/role_list";
    }
}
