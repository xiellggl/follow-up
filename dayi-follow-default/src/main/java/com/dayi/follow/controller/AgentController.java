package com.dayi.follow.controller;

import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.BankTypeEnum;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.SearchVo;
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
    @RequestMapping("/list")
    public String customerAgentCompile(HttpServletRequest request, Model model, SearchVo searchVo) {
        LoginVo currVo = userComponent.getCurrUser(request);//需要加上loginType=followUp参数
        String flowId = currVo.getId();  // 跟进人ID

        Page page=new Page();

        page = this.myCustomerQuery(Constants.SEARCH_PAGE_SIZE, page, flowId, 1,searchVo);
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
        return "uc/customer/agent/list";
    }


    /* 我的客户查询 -- 抽取通用查询方法 */
    private Page<AgentListVo> myCustomerQuery(Integer pageSize, Page<AgentListVo> page, String followId, Integer flowTypeTab, SearchVo searchVo) {
        List<String> bankTypeList = searchVo.getBankType();

        StringBuffer bankTypeBuf = new StringBuffer();
        if (bankTypeList != null && bankTypeList.size() != 0) {
            for (int i = 0; i < bankTypeList.size(); i++) {
                if (i < bankTypeList.size() - 1) {
                    bankTypeBuf.append(bankTypeList.get(i)).append(","); //组成这种形式发过来1,4,5
                } else {
                    bankTypeBuf.append(bankTypeList.get(i));
                }
            }
        }
        String bankType = bankTypeBuf.toString();
        searchVo.setBankTypeStr(bankType);

        if (page != null) {
            page.setPageSize(pageSize);
        }
        if (flowTypeTab.equals(1)) { // 查询 代理商
            return agentService.findAgentPage(page, searchVo,followId,null,null, null, null);
        } else { // 查询 创客
//            return financeAgentService.getOrgCustomerPage(page, linkStatus, nameValidata, mobile,
//                    makerNum, null, null, flowId, null, null);
            return null;
        }
    }





}
