package com.dayi.follow.controller;

import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/followup")
public class FollowupController extends BaseController {
    @Resource
    FollowUpService followUpService;
    @Resource
    UserComponent userComponent;
    @Resource
    DeptService deptService;
    private static Logger logger = LoggerFactory.getLogger(FollowupController.class);

    @RequestMapping(value = "/list")
    public String list(HttpServletRequest request, Model model, Page page) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String mobile = request.getParameter("mobile");
        String queryDeptId = request.getParameter("deptId");
        String inviteCode = request.getParameter("inviteCode");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = followUpService.findPage(page, currVo.getDeptId(), mobile, queryDeptId, inviteCode);

        List<Department> deptList = deptService.getSubDept(currVo.getDeptId());

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("deptList", deptList);  // 所属部门下拉选择数据
        model.addAttribute("queryDeptId", queryDeptId);  // 查询条件 -- 所属部门
        return "/followup/manage/followuper/list";
    }


    @RequestMapping(value = "/agentList")
    public String agentList(HttpServletRequest request, Model model, Page page) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String followId = request.getParameter("followId");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        followUpService.findAgentPage(page,currVo.getDeptId(),followId);

        return "/followup/manage/followuper/list";
    }

    @RequestMapping(value = "/orgList")
    public String orgList(HttpServletRequest request, Model model, Page page) {
        return "/followup/manage/followuper/list";
    }

}
