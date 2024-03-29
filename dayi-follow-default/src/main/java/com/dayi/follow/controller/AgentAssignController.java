package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.*;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.export.AgentAssignExport;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/agent/assign")
public class AgentAssignController extends BaseController {
    @Resource
    FollowUpService followUpService;
    @Resource
    UserComponent userComponent;
    @Resource
    FollowAgentService followAgentService;
    @Resource
    AgentService agentService;

    /**
     * 代理商分配列表
     */
    @RequestMapping("/list")
    public String assignList(HttpServletRequest request, Model model, Page page, SearchVo searchVo) {
        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        page = followAgentService.findAssignPage(page, searchVo);

        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        return "/agent/assign_list";
    }

    /**
     * 代理商分配列表导出
     */
    @RequestMapping(value = "/list/export")
    @ResponseBody
    public void orgExport(HttpServletRequest request, HttpServletResponse response, SearchVo searchVo) throws IOException {
        List assignList = followAgentService.findAssignList(searchVo);

        String title = "代理商分配列表";
        String fileName = title + new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        AgentAssignExport export = new AgentAssignExport(fileName, title, assignList);
        export.exportExcel(request, response);
    }

    /**
     * 跟进人分配 -- 选择弹窗列表
     */
    @RequestMapping(value = "/select")
    public String assignSelect(HttpServletRequest request, Model model, Page page) {

        String followUpStr = request.getParameter("followUp");
        String followId = request.getParameter("followId");
        FollowUp followUp = followUpService.get(followId);

        page = followUpService.findAssignSelect(page, followUpStr);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("followUp", followUp);
        model.addAttribute("page", page);
        model.addAttribute("pageUrl", pageUrl);
        return "/followup/select_list";
    }


    /**
     * 跟进人分配 -- 代理商 -- 分配保存
     */
    @RequestMapping("/save/batch")
    @ResponseBody
    public BizResult assignSave(@RequestParam("agentIds") String agentIds, @RequestParam("followId") String followId) {
        return followAgentService.addBatch(agentIds, followId);
    }


    /**
     * 跟进人分配 -- 代理商  -- 清除分配
     */
    @RequestMapping("/clear/batch")
    @ResponseBody
    public BizResult assignClear(HttpServletRequest request, @RequestParam String ids) {
        List<FollowAgent> followAgents = new ArrayList<FollowAgent>();

        String[] split = StringUtils.split(ids, ",");
        for (String s : split) {
            FollowAgent followAgent = followAgentService.getFollowAgentByAgentId(Integer.valueOf(s));
            if (followAgent == null) return BizResult.FAIL;
            followAgents.add(followAgent);
        }
        return followAgentService.clearBatch(followAgents);
    }


}