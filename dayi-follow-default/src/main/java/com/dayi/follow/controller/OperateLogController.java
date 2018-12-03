package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.Misc;
import com.dayi.follow.model.follow.Menu;
import com.dayi.follow.model.follow.Module;
import com.dayi.follow.service.ModuleService;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/operatelog")
public class OperateLogController {

    @Resource
    ModuleService moduleService;
    @Resource
    PermissionService permissionService;

    /**
     * 列表查询
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {

        return "";
    }



}
