package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.Misc;
import com.dayi.follow.model.follow.Module;
import com.dayi.follow.service.ModuleService;
import com.dayi.follow.model.follow.Menu;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.vo.PermissionVo;
import com.dayi.follow.vo.sys.ModuleSearchVo;
import com.dayi.mybatis.support.Page;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     * 所有菜单
     */
    @RequestMapping("/menus")
    @ResponseBody
    public BizResult menus() {
        List<Menu> menus=moduleService.queryMenus("", false, true, new Module(), new PermissionVo());
        return BizResult.succ(menus);
    }

    /**
     * 新增模块
     */
    @RequestMapping("/add/save")
    @ResponseBody
    public BizResult addSave(HttpServletRequest request, Module module) {
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
    public BizResult edit(Module module) {
        return BizResult.succ(moduleService.getModule(module.getId()));
    }

    /**
     * 编辑保存模块
     */
    @RequestMapping("/edit/save")
    @ResponseBody
    public BizResult save(HttpServletRequest request, Module module) {
        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        if (null == accountInfo) {
            return BizResult.fail("请先登录.");
        }
        module.setUpdateBy(accountInfo.getUserName());

        return moduleService.updateModule(module) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BizResult delete(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        if (!Misc.isEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                moduleService.deleteModule(id);
            }
        }
        return BizResult.SUCCESS;
    }

    /**
     * 列表查询
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
