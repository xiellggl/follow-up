package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.Misc;
import com.dayi.follow.model.follow.Module;
import com.dayi.follow.service.ModuleService;
import com.dayi.follow.model.follow.Menu;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.vo.PermissionVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/followup/module")
public class ModuleController {

    @Resource
    ModuleService moduleService;
    @Resource
    PermissionService permissionService;
    /**
     * 所有菜单
     */
    @RequestMapping("/menus")
    public BizResult menus() {
        List<Menu> menus=moduleService.queryMenus("", false, true, new Module(), new PermissionVo());
        return BizResult.succ(menus);
    }

    /**
     * 新增模块
     */
    @RequestMapping("/add/save")
    public BizResult addSave(Module module) {
        return moduleService.addModule(module) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 编辑模块
     */
    @RequestMapping("/edit")
    public BizResult edit(Module module) {
        return BizResult.succ(moduleService.getModule(module.getId()));
    }


    /**
     * 编辑保存模块
     */
    @RequestMapping("/edit/save")
    public BizResult save(Module module) {
            return moduleService.updateModule(module) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public BizResult delete(HttpServletRequest request) {
        String ids = request.getParameter("id");
        if (!Misc.isEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                moduleService.deleteModule(id);
            }
        }
        return BizResult.SUCCESS;
    }




}
