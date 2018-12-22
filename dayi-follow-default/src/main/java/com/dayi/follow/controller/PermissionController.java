package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.Misc;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.model.follow.Menu;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.service.ModuleService;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.sys.PermissionSearchVo;
import com.dayi.mybatis.support.Page;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    ModuleService moduleService;

    @Resource
    PermissionService permissionService;

    /**
     * 查询功能
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, PermissionSearchVo permissionSearchVo, Model model) {
        //功能页面
        Page<Permission> page =  permissionService.searchPermissions(permissionSearchVo);
        page.setPageSize(30);
        model.addAttribute("page", page);

        //模块列表
        List<Menu> menus = moduleService.listModule();
        model.addAttribute("menus", menus);

        //功能页面列表
        List<Permission> parentList = permissionService.listParent();
        model.addAttribute("parentList", parentList);

        //分页：当前URL
        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("pageUrl", pageUrl);

        return "sys/binding_list";
    }

    /**
     * 添加功能
     * @param permission
     * @return
     */
    @RequestMapping("/add/save")
    @ResponseBody
    public BizResult addSave(HttpServletRequest request, Permission permission) {
        // 获取用户信息，设置操作人
        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        if (null == accountInfo) {
            return BizResult.fail("请先登录");
        }
        permission.setCreateBy(accountInfo.getUserName());

        return permissionService.addPermission(permission) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 编辑功能
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(String id, Model model) {
        //当前功能信息
        Permission permission = permissionService.get(id);
        model.addAttribute("permission", permission);

        //模块列表
        List<Menu> menus = moduleService.listModule();
        model.addAttribute("menus", menus);

        //功能页面列表
        List<Permission> parentList = permissionService.listParent();
        model.addAttribute("parentList", parentList);

        return "sys/binding_edit";
    }

    /**
     * 编辑保存功能
     * @param permission
     * @return
     */
    @RequestMapping("/edit/save")
    @ResponseBody
    public BizResult editSave(HttpServletRequest request, Permission permission) {
        // 获取用户信息，设置操作人
        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        if (null == accountInfo) {
            return BizResult.fail("请先登录");
        }
        permission.setUpdateBy(accountInfo.getUserName());

        return permissionService.updatePermission(permission);
    }

    /**
     * 显示/隐藏功能
     * @param id
     * @param displace
     * @return
     */
    @RequestMapping("/displace")
    @ResponseBody
    public BizResult displacePermission(String id, boolean displace) {
        if (Misc.isEmpty(id)) {
            return BizResult.fail("请选择要显示/隐藏的功能");
        }
        return permissionService.updateDisplace(id, displace);
    }

    /**
     * 绑定功能提交
     */
    @RequestMapping("/bind/save")
    @ResponseBody
    public BizResult bindSave(HttpServletRequest request) {
        String moduleId = request.getParameter("moduleId");
        String permissionIds = request.getParameter("permissionIds");
        if (Misc.isEmpty(moduleId)) {
            return BizResult.fail("请选择模块");
        }
        if (Misc.isEmpty(permissionIds)) {
            return BizResult.fail("请选择功能");
        }

        if (permissionService.bindModule(moduleId, permissionIds)) {
            return BizResult.SUCCESS;
        }
        return BizResult.FAIL;
    }

    /**
     * 解绑功能提交
     */
    @RequestMapping("/untying/save")
    @ResponseBody
    public BizResult untyingSave(HttpServletRequest request) {
        String permissionId = request.getParameter("id");
        if (Misc.isEmpty(permissionId)) {
            return BizResult.fail("请选择功能");
        }

        if (permissionService.untyingModule(permissionId)) {
            return BizResult.SUCCESS;
        }
        return BizResult.FAIL;
    }

    /**
     * 删除权限
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
                if (!permissionService.delete(id)) {
                    return BizResult.FAIL;
                }
            }
        }
        return BizResult.SUCCESS;
    }
}
