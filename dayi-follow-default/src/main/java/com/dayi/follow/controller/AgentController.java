package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.AgentIntenTypeEnum;
import com.dayi.follow.enums.BankTypeEnum;
import com.dayi.follow.enums.ContactTypeEnum;
import com.dayi.follow.model.follow.AgentContact;
import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.FollowAgentService;
import com.dayi.follow.service.FollowOrgService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.util.CollectionUtil;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.*;
import com.dayi.follow.vo.agent.DetailVo;
import com.dayi.mybatis.common.util.Misc;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/agent")
public class AgentController {
    @Resource
    FollowUpService followUpService;
    @Resource
    UserComponent userComponent;
    @Resource
    AgentService agentService;
    @Resource
    FollowAgentService followAgentService;
    @Resource
    FollowOrgService followOrgService;

    /**
     * 我的客户-代理商列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String agentList(HttpServletRequest request, Model model, SearchVo searchVo, Page page) {
        LoginVo currVo = userComponent.getCurrUser(request);
        String followId = currVo.getId();
        page.setPageSize(Constants.SEARCH_PAGE_SIZE);
        //处理银行搜索参数
        searchVo.setBankTypeStr(CollectionUtil.getStr(searchVo.getBankType()));

        page = agentService.findAgentPage(page, searchVo, followId);
        //处理银行枚举
        BankTypeEnum[] bankTypes = ArrayUtils.removeElements(BankTypeEnum.values(), BankTypeEnum.Ping_An, BankTypeEnum.Ping_An_Card);

        String queryStr = request.getQueryString();//返回地址
        String returnUrl = StringUtil.urlEncode(queryStr);
        model.addAttribute("returnUrl", returnUrl);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        String[] bankTypesArr = request.getParameterValues("bankType");   //结算银行搜索参数

        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("customerTypes", AgentCusTypeEnum.values());//客户类型
        model.addAttribute("bankTypes", bankTypes);//银行类型
        model.addAttribute("bankTypesArr", bankTypesArr);
        return "uc/customer/agent/list";
    }

    /**
     * 我的客户-代理商详细
     *
     * @param request
     * @return
     */
    @RequestMapping("/detail")
    public String agentDetail(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        Integer agentId = Misc.toInt(request.getParameter("agentId"), 0);// 代理人ID

        String followId = followAgentService.getFollowIdByAgentId(agentId);

        DetailVo detailVo = new DetailVo();

        if (currVo.getId().equals(followId)) {//客户属于当前登陆者
            detailVo = followAgentService.getDetail(agentId);//代理商明细
        } else {
            return "redirect:/followup/uc/index";
        }

        String returnUrl = request.getParameter("returnUrl");
        model.addAttribute("detailVo", detailVo);//明细
        model.addAttribute("customerTypes", AgentCusTypeEnum.values());//客户类型
        model.addAttribute("contactTypes", ContactTypeEnum.values());//联系方式
        model.addAttribute("customerIntentionTypes", AgentIntenTypeEnum.values());//客户意向度
        model.addAttribute("returnUrl", returnUrl);//返回代理商进来列表的路径
        return "uc/customer/agent/detail";
    }

    /**
     * 代理商登录日志
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginlog")
    public String loginlog(HttpServletRequest request, Model model, Page page) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        Integer agentId = Misc.toInt(request.getParameter("agentId"), 0);// 代理人ID

        String followId = followAgentService.getFollowIdByAgentId(agentId);

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        if (currVo.getId() == followId) {
            page = agentService.findLoginLog(page, agentId);
        } else {
            return "redirect:/followup/uc/index";
        }

        model.addAttribute("page", page);//登录日志
        model.addAttribute("pageUrl", pageUrl);
        return "/followup/uc/customer/login_list";
    }

    /**
     * 代理商联系记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/contact")
    public String contact(HttpServletRequest request, Model model, Page page) {//page不接受代理商的分页
        LoginVo currVo = userComponent.getCurrUser(request);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        Integer agentId = Misc.toInt(request.getParameter("agentId"), 0);// 代理人ID

        String followId = followAgentService.getFollowIdByAgentId(agentId);

        page.setPageSize(Constants.CONTACT_PAGE_SIZE);

        if (currVo.getId() == followId) {
            page = followAgentService.findContacts(page, agentId);
        } else {
            return "redirect:/followup/uc/index";
        }

        String returnUrl = request.getParameter("returnUrl");//返回代理商进来列表的路径

        model.addAttribute("page", page);//联系时间取createDate
        model.addAttribute("returnUrl", returnUrl);//返回代理商进来列表的路径
        request.setAttribute("pageUrl", pageUrl);
        return "/followup/uc/customer/contact_list";
    }

    /**
     * 返回添加代理商联系记录所需数据
     *
     * @param request
     * @return
     */
    @RequestMapping("/contact/add")
    public String contactAdd(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        Integer agentId = Misc.toInt(request.getParameter("agentId"), 0);// 代理人ID

        String followId = followAgentService.getFollowIdByAgentId(agentId);

        FollowAgent followAgent = new FollowAgent();
        if (currVo.getId() == followId) {
            followAgent = followAgentService.getFollowAgentByAgentId(agentId);
        } else {
            return "redirect:/followup/uc/index";
        }

        model.addAttribute("agent", followAgent);
        return "/followup/uc/customer/contact_list";
    }

    /**
     * 添加代理商联系记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/contact/add/save")
    @ResponseBody
    public BizResult contactAddSave(HttpServletRequest request, AgentContact agentContact) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String followId = followAgentService.getFollowIdByAgentId(agentContact.getAgentId());

        if (currVo.getId() == followId) {
            agentContact.setFollowUp(currVo.getName());
            agentContact.setFlowId(currVo.getId());
            return agentService.addContact(agentContact);
        } else {
            return BizResult.fail("无法操作此代理商！");
        }
    }


}