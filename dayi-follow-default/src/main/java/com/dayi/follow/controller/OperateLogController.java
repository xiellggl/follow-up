package com.dayi.follow.controller;

import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.service.OperateLogService;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.sys.OperateLogSearchVo;
import com.dayi.mybatis.support.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/operatelog")
public class OperateLogController {

    @Resource
    private OperateLogService operateLogService;

    /**
     * 列表查询
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, OperateLogSearchVo operateLogSearchVo, Model model) {
        Page<OperateLog> page = operateLogService.searchOperateLog(operateLogSearchVo);
        model.addAttribute("page", page);
        //分页：当前URL
        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("pageUrl", pageUrl);
        return "sys/log/system_log";
    }
}
