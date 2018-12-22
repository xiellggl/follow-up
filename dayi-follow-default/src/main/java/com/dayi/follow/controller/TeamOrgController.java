package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.enums.OrgTypeEnum;
import com.dayi.follow.model.follow.OrgContact;
import com.dayi.follow.service.FollowOrgService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.common.util.Misc;
import com.dayi.mybatis.support.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/team/org")
public class TeamOrgController {
    @Resource
    FollowUpService followUpService;
    @Resource
    UserComponent userComponent;
    @Resource
    FollowOrgService followOrgService;
    @Resource
    OrgService orgService;

    /**
     * 我的客户-创客列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String agentList(HttpServletRequest request, Model model, SearchVo vo, Page page) {
        LoginVo currVo = userComponent.getCurrUser(request);
        page.setPageSize(Constants.SEARCH_PAGE_SIZE);

        page = orgService.findTeamOrgPage(page, vo, currVo.getId(), currVo.getDeptId());

        String queryStr = request.getQueryString();//返回地址
        String returnUrl = StringUtil.urlEncode(queryStr);
        model.addAttribute("returnUrl", returnUrl);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("orgTypes", OrgTypeEnum.values());//机构商类型
        return "uc/customer/team/maker";
    }

    /**
     * 创客联系记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/contact")
    public String contact(HttpServletRequest request, Model model, Page page) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        Integer orgId = Misc.toInt(request.getParameter("orgId"), 0);// 创客人ID

        String followId = followOrgService.getFollowIdByOrgId(orgId);

        List<String> followIds = followUpService.findIdsByDeptId(currVo.getDeptId());

        page.setPageSize(Constants.CONTACT_PAGE_SIZE);

        if (followIds.contains(followId)) {
            page = followOrgService.findContacts(page, orgId);
        } else {
            return "redirect:/followup/uc/index";
        }

        String returnUrl = request.getParameter("returnUrl");//返回创客进来列表的路径

        model.addAttribute("page", page);//联系时间取createDate
        model.addAttribute("returnUrl", returnUrl);//返回创客进来列表的路径
        request.setAttribute("pageUrl", pageUrl);
        return "/followup/uc/customer/contact_list";
    }
}