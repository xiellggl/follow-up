package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.Misc;
import com.dayi.follow.model.follow.Module;
import com.dayi.follow.service.ModuleService;
import com.dayi.follow.model.follow.Menu;
import com.dayi.follow.vo.PermissionVo;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import com.dayi.user.authorization.authz.AuthorizationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
     * 加载所有模块菜单
     * @return
     */
    @RequestMapping("/listAll")
    @ResponseBody
    public BizResult listAll(Boolean isOnlyShowEnable) {
        List<Menu> menus = moduleService.listAll(isOnlyShowEnable);
        return BizResult.succ(menus);
    }

    /**
     * 加载所有模块（不包括菜单）
     * @return
     */
    @RequestMapping("/listModule")
    @ResponseBody
    public BizResult listModule() {
        List<Menu> menus = moduleService.listModule();
        return BizResult.succ(menus);
    }

    /**
     * 查询模块列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<Menu> menus = moduleService.listAll(null);
        model.addAttribute("menus", menus);
        return "sys/module_list";
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
    public String edit(String id, Model model) {
        Module module = moduleService.getModule(id);
        model.addAttribute("module", module);
        return "sys/module_edit";
    }

    /**
     * 获取模块信息
     */
    @RequestMapping("/getModuleById")
    @ResponseBody
    public BizResult getModuleById(String id) {
        Module module = moduleService.getModule(id);
        return BizResult.succ(module, "操作成功！");
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

        return moduleService.updateModule(module);
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
        return moduleService.updateStatus(id, enable);
    }


    /**
     * 解绑功能
     */
    @RequestMapping("/untying")
    @ResponseBody
    public BizResult untyingSave(HttpServletRequest request) {
        String moduleId = request.getParameter("id");
        if (Misc.isEmpty(moduleId)) {
            return BizResult.fail("请选择模块");
        }

        if (moduleService.untying(moduleId)) {
            return BizResult.SUCCESS;
        }
        return BizResult.FAIL;
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
