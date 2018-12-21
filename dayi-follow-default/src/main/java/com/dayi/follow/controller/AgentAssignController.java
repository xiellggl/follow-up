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
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/agent/assign")
public class AgentAssignController extends BaseController{
    @Resource
    FollowUpService followUpService;
    @Resource
    UserComponent userComponent;
    @Resource
    FollowOrgService followOrgService;
    @Resource
    OrgService orgService;
    @Resource
    FollowAgentService followAgentService;
    @Resource
    AgentService agentService;

    /**
     * 代理商分配列表
     */
    @RequestMapping("/list")
    public String assignList(HttpServletRequest request, Model model, Page page, SearchVo searchVo) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        page = followAgentService.findAssignPage(page, searchVo, currVo.getDeptId());

        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        return "/manage/followuper/agent_assign_list";
    }

    /**
     * 跟进人分配 -- 选择弹窗列表
     */
    @RequestMapping(value = "/select")
    public String assignSelect(HttpServletRequest request, Model model, Page page) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String followUpStr = request.getParameter("followUp");
        String followId = request.getParameter("followId");
        FollowUp followUp = followUpService.get(followId);

        page = followUpService.findAssignSelect(page, followUpStr, currVo.getDeptId());

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("followUp", followUp);
        model.addAttribute("page", page);
        model.addAttribute("pageUrl", pageUrl);
        return "/manage/followuper/assign_select";
    }

    /**
     * 跟进人分配 -- 代理商 -- 分配保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public BizResult assignSave(HttpServletRequest request, @Valid FollowAgent followAgent, BindingResult result) {
        BizResult bizResult = checkErrors(result);

        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        return followAgentService.add(followAgent);
    }

    /**
     * 跟进人分配 -- 代理商  -- 清除分配
     */
    @RequestMapping("/clear")
    @ResponseBody
    public BizResult assignClear(HttpServletRequest request, @RequestParam Integer id) {
        FollowAgent followAgent = followAgentService.getFollowAgentByAgentId(id);

        if (followAgent == null) return BizResult.FAIL;

        return followAgentService.clear(followAgent);
    }

    /**
     * 跟进人分配 -- 代理商 -- 分配保存
     */
    @RequestMapping("/save/batch")
    @ResponseBody
    public BizResult assignSave(HttpServletRequest request, @RequestParam("agentIds") String agentIds, @RequestParam("followId") String followId) {
        List<FollowAgent> followAgents = new ArrayList<FollowAgent>();

        String[] split = StringUtils.split(agentIds, ",");
        for (String s : split) {
            Agent agent = agentService.get(Integer.valueOf(s));
            if (agent == null) return BizResult.FAIL;

            FollowAgent followAgent = new FollowAgent();
            followAgent.setAgentId(Integer.valueOf(s));
            followAgent.setFollowId(followId);

            followAgents.add(followAgent);
        }
        return followAgentService.addBatch(followAgents);

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