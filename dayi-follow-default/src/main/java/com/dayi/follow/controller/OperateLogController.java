package com.dayi.follow.controller;

import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.service.OperateLogService;
import com.dayi.follow.vo.sys.OperateLogSearchVo;
import com.dayi.mybatis.support.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
    public String list(OperateLogSearchVo operateLogSearchVo, Model model) {
        Page<OperateLog> page = operateLogService.searchOperateLog(operateLogSearchVo);
        model.addAttribute("page", page);
        return "sys/log/system_log";
    }
}
