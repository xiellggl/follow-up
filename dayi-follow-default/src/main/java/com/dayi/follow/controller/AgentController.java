package com.dayi.follow.controller;

import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.BankTypeEnum;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.AgentVo;
import com.dayi.mybatis.common.util.Misc;
import com.dayi.mybatis.support.Page;
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


    /**
     * 我的客户-代理商列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/agent/list")
    public String customerAgentCompile(HttpServletRequest request, Model model) {
        FollowUp currUser = userComponent.getCurrUser(request);//需要加上loginType=followUp参数
        if (currUser == null) {
            return "redirect:/followup/login";
        }
        if ((currUser.getIsAdmin().equals(1)) || ("admin".equals(currUser.getUserName()))) {
            return "redirect:/followup/manage/index";
        }
        String flowId = currUser.getId();  // 跟进人ID

        Page page=new Page();

        page = this.myCustomerQuery(request, Constants.SEARCH_PAGE_SIZE, page, flowId, 1);
        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        BankTypeEnum[] values = BankTypeEnum.values();
        List<BankTypeEnum> bankTypes = new ArrayList<BankTypeEnum>();
        Collections.addAll(bankTypes, values);
        Iterator<BankTypeEnum> iterator = bankTypes.iterator();
        while (iterator.hasNext()) {
            BankTypeEnum next = iterator.next();
            if (BankTypeEnum.Ping_An.equals(next) || BankTypeEnum.Ping_An_Card.equals(next)) {
                iterator.remove();
            }
        }
        String queryStr = request.getQueryString();
        String returnUrl =StringUtil.urlEncode(queryStr);
        String[] bankTypesArr = request.getParameterValues("bankType");   //结算银行搜索参数
        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("customerTypes", AgentCusTypeEnum.values());//客户类型
        model.addAttribute("bankTypes", bankTypes);//银行类型
        model.addAttribute("bankTypesArr", bankTypesArr);
        model.addAttribute("returnUrl", returnUrl);
        return "/followup/uc/customer/agent/list";
    }


    /* 我的客户查询 -- 抽取通用查询方法 */
    private Page<AgentVo> myCustomerQuery(HttpServletRequest request, Integer pageSize, Page<AgentVo> page, String flowId, Integer flowTypeTab) {
        Integer linkStatus = Misc.toInt(request.getParameter("linkStatus"), -1);  // 是否联系
        Integer bankSign = Misc.toInt(request.getParameter("bankSign"), -1);  // 是否绑卡
        Integer idCardValid = Misc.toInt(request.getParameter("idCardVal"), -1);  // 代理商 -- 实名认证
        Integer nameValidata = Misc.toInt(request.getParameter("nameValidata"), -1);  // 机构商 -- 实名认证
        Integer inCash = Misc.toInt(request.getParameter("inCash"), -1);  // 是否入金
        Integer totalFund = Misc.toInt(request.getParameter("totalFund"), -1);  // 总资产为零：1--勾选为零
        String mobile = request.getParameter("mobile");  // 手机号
        String inviteCode = request.getParameter("inviteCode");  // 邀请码
        String makerNum = request.getParameter("makerNum");  // 邀请码(创客号)
        Integer todayInCash = Misc.toInt(request.getParameter("todayInCash"), -1);  // 今日是否入金
        Integer todayOutCash = Misc.toInt(request.getParameter("todayOutCash"), -1);  // 今日是否出金
        Integer customerType = Misc.toInt(request.getParameter("customerType"), -1);  // 客户类型
        Integer totalBalance = Misc.toInt(request.getParameter("totalBalance"), -1);  // 客户总资产分类

        String[] bankTypeArr = request.getParameterValues("bankType");//银行类型，1-中信银行，4-广发银行，5-华夏银行
        StringBuffer bankTypeBuf = new StringBuffer();
        if (bankTypeArr != null && bankTypeArr.length != 0) {
            for (int i = 0; i < bankTypeArr.length; i++) {
                if (i < bankTypeArr.length - 1) {
                    bankTypeBuf.append(bankTypeArr[i]).append(","); //组成这种形式发过来1,4,5
                } else {
                    bankTypeBuf.append(bankTypeArr[i]);
                }
            }
        }
        String bankType = bankTypeBuf.toString();

        Integer waitToLinkToday = Misc.toInt(request.getParameter("waitToLinkToday"), -1);  // 是否查询今日待联系
        if (page != null) {
            page.setPageSize(pageSize);
        }
        if (flowTypeTab.equals(1)) { // 查询 代理商
            return agentService.findAgentPage(page, linkStatus, idCardValid, bankSign, inCash, totalFund, mobile,
                    inviteCode, null, null, todayInCash, todayOutCash, customerType, totalBalance, bankType,
                    waitToLinkToday, flowId, null, null);
        } else { // 查询 创客
//            return financeAgentService.getOrgCustomerPage(page, linkStatus, nameValidata, mobile,
//                    makerNum, null, null, flowId, null, null);
            return null;
        }
    }





}
