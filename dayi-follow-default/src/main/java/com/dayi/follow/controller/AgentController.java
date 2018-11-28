package com.dayi.follow.controller;

import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.AgentIntenTypeEnum;
import com.dayi.follow.enums.BankTypeEnum;
import com.dayi.follow.enums.ContactTypeEnum;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.FollowAgentService;
import com.dayi.follow.service.FollowOrgService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.util.CollectionUtil;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.*;
import com.dayi.mybatis.common.util.Misc;
import com.dayi.mybatis.support.Page;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/followup/agent")
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
    public String agentList(HttpServletRequest request, Model model, SearchVo searchVo) {
        LoginVo currVo = userComponent.getCurrUser(request);
        String followId = currVo.getId();
        Page page = new Page();
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

    @RequestMapping("/detail")
    public String agentDetail(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        Integer agentId = Misc.toInt(request.getParameter("agentId"), 0);// 代理人ID

        String followId = followAgentService.getFollowIdByAgentId(agentId);

        DetailVo detailVo = new DetailVo();

        if (currVo.getId() == followId) {//客户属于当前登陆者
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
        return "/followup/uc/customer/agent/detail";
    }

    /**
     * 代理商登录日志
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginlog")
    public String loginlog(HttpServletRequest request, Model model, Page page) {
        FlowUpLoginVo vo = financeUserComponent.getCurrentLoginFollowUp(request);
        if (vo == null) {
            return "redirect:/followup/login";
        }
        if ((vo.getIsAdmin().equals(1)) || ("admin".equals(vo.getUserName()))) {
            return "redirect:/followup/manage/index";
        }
        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        Integer agentId = Misc.toInt(request.getParameter("agentId"));// 代理人ID
        Integer belong = judgeCusBelong(agentId, null, vo);//判断客户是否属于当前跟进人，或当前登录用户是团队负责人
        if (belong.equals(0) || agentId.equals(0)) {
            return "redirect:/followup/uc/index";
        }
        List<PropertyFilter> filterList = PropertyFilter.buildFromHttpRequest(request);
        Page<FinanceLoginLogVo> logVoPage = this.customerLoginLogQuery(Constants.DEFAULT_PAGE_SIZE, page, filterList, agentId);
        model.addAttribute("page", logVoPage);//登录日志
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
    public String customerAgent_compile(HttpServletRequest request, Model model, Page page) {//page不接受代理商的分页
        FlowUpLoginVo vo = financeUserComponent.getCurrentLoginFollowUp(request);
        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        Integer agentId = Misc.toInt(request.getParameter("agentId"));// 代理人ID
        Integer belong = judgeCusBelong(agentId, null, vo);//判断客户是否属于当前跟进人，或当前登录用户是团队负责人
        if (belong.equals(0) || agentId.equals(0)) {
            return "redirect:/followup/uc/index";
        }
        Page<AgentContactVo> agentContactVoPage = this.contactQuery(page, agentId);//联系记录分页
        String returnUrl = request.getParameter("returnUrl");//返回代理商进来列表的路径
        model.addAttribute("nextAgentVo", null);//如果nextAgentVo为空
        model.addAttribute("page", agentContactVoPage);//联系时间取createDate
        model.addAttribute("returnUrl", returnUrl);//返回代理商进来列表的路径
        request.setAttribute("pageUrl", pageUrl);
        request.setAttribute("agentId", agentId);
        return "/followup/uc/customer/contact_list";
    }

}