package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.model.follow.OrgContact;
import com.dayi.follow.service.*;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.mybatis.common.util.Misc;
import com.dayi.mybatis.support.Page;
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
@RequestMapping("/org")
public class OrgController {
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
    public String agentList(HttpServletRequest request, Model model, Page page) {
        LoginVo currVo = userComponent.getCurrUser(request);
        page.setPageSize(Constants.SEARCH_PAGE_SIZE);

        String mobile = request.getParameter("mobile");
        String inviteCode = request.getParameter("inviteCode");

        page = orgService.findOrgPage(page, mobile, inviteCode, currVo.getId());

        String queryStr = request.getQueryString();//返回地址
        String returnUrl = StringUtil.urlEncode(queryStr);
        model.addAttribute("returnUrl", returnUrl);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        return "uc/customer/maker/list";
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

        page.setPageSize(Constants.CONTACT_PAGE_SIZE);

        if (currVo.getId() == followId) {
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

    /**
     * 返回添加创客联系记录所需数据
     *
     * @param request
     * @return
     */
    @RequestMapping("/contact/add")
    @ResponseBody
    public BizResult contactAdd(HttpServletRequest request) {
        LoginVo currVo = userComponent.getCurrUser(request);

        Integer orgId = Misc.toInt(request.getParameter("orgId"), 0);// 创客人ID

        String followId = followOrgService.getFollowIdByOrgId(orgId);

        if (currVo.getId() != followId) return BizResult.FAIL;
        return BizResult.SUCCESS;
    }

    /**
     * 添加创客联系记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/contact/add/save")
    @ResponseBody
    public BizResult contactAddSave(HttpServletRequest request, OrgContact orgContact) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String followId = followOrgService.getFollowIdByOrgId(orgContact.getOrgId());

        if (currVo.getId() == followId) {
            orgContact.setFollowUp(currVo.getName());
            orgContact.setFlowId(currVo.getId());

            return orgService.addContact(orgContact);
        } else {
            return BizResult.fail("无法操作此创客！");
        }
    }


}