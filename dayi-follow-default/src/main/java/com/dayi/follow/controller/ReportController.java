package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.DateUtil;
import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.service.ReportService;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    @Resource
    private UserComponent userComponent;

    @Resource
    private DeptService deptService;
    @Resource
    private FollowUpService followUpService;
    @Resource
    private ReportService reportService;

    /**
     * 日报
     *
     * @param request
     * @return
     */
    @RequestMapping("/daily")
    public String saleDaily(HttpServletRequest request, Page page, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");//查询条件

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page= reportService.findDailyPage(page,currVo.getId(),date);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("date", date);
        return "/followup/uc/log/mydaily";
    }

    /**
     * 团队日报
     *
     * @param request
     * @return
     */
    @RequestMapping("/team/daily")
    public String kaDaily(HttpServletRequest request, Page page, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");//查询条件

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page= reportService.findTeamDaily(page,currVo.getId(),date);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("date", date);
        return "/followup/uc/log/mydaily";
    }







}
