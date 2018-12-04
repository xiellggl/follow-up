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
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.export.AdminWeekReportExport;
import com.dayi.follow.vo.export.TeamDailyDetailExport;
import com.dayi.follow.vo.report.AdminWeekVo;
import com.dayi.follow.vo.report.ReportDailyVo;
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
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Calendar;
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

        page = reportService.findDaily(page, currVo.getId(), date);

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

        page = reportService.findTeamDaily(page, currVo.getId(), date);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("date", date);
        return "/followup/uc/log/mydaily";
    }

    /**
     * 团队日报详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/team/detail/")
    public String teamDailyDetail(HttpServletRequest request, Page page, Model model) {
        String deptId = request.getParameter("deptId");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = reportService.findTeamDailyDetail(page, deptId);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        return "/followup/uc/log/mydaily";
    }

    /**
     * 团队日报详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/team/detail/export")
    public void teamDetailExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginVo currVo = userComponent.getCurrUser(request);
        String deptId = request.getParameter("deptId");

        List<ReportDailyVo> reportDailyVos = reportService.exportTeamDailyDetail(deptId);

        String fileTitle = "团队日报详情";
        String fileName = currVo.getName() + "-" + fileTitle + new DateTime().toString("yyyy-MM-dd HH:mm:ss");

        TeamDailyDetailExport export = new TeamDailyDetailExport(fileName, fileTitle, reportDailyVos);
        export.exportExcel(request, response);
    }

    /**
     * 周报
     *
     * @param request
     * @return
     */
    @RequestMapping("/week")
    public String week(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        ReportDailyVo week = reportService.getWeek(currVo.getId(), date);

        model.addAttribute("week", week);
        return "/followup/uc/log/mydaily";
    }

    /**
     * 团队周报
     *
     * @param request
     * @return
     */
    @RequestMapping("/team/week")
    public String teamWeek(HttpServletRequest request, Page page, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        List<ReportDailyVo> teamWeek = reportService.findTeamWeek(currVo.getDeptId(), date);

        model.addAttribute("teamWeek", teamWeek);
        return "/followup/uc/log/mydaily";
    }

    /**
     * 周报
     *
     * @param request
     * @return
     */
    @RequestMapping("/month")
    public String month(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        ReportDailyVo week = reportService.getWeek(currVo.getId(), date);

        model.addAttribute("week", week);
        return "/followup/uc/log/mydaily";
    }

    /**
     * 团队周报
     *
     * @param request
     * @return
     */
    @RequestMapping("/team/month")
    public String teamMonth(HttpServletRequest request, Page page, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        List<ReportDailyVo> teamMonth = reportService.findTeamMonth(currVo.getDeptId(), date);

        model.addAttribute("teamMonth", teamMonth);
        return "/followup/uc/log/mydaily";
    }

    /**
     * 管理员日报
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/daily")
    public String adminDaily(HttpServletRequest request, Page page, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");
        String deptName = request.getParameter("deptName");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = reportService.findAdminDaily(page, currVo.getDeptId(), deptName, date);

        model.addAttribute("page", page);
        return "/followup/uc/log/mydaily";
    }

    /**
     * 管理员周报
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/week")
    public String adminWeek(HttpServletRequest request, Page page, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = reportService.findAdminWeek(page, currVo.getDeptId(), date);

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        model.addAttribute("page", page);
        return "/followup/uc/log/mydaily";
    }

    /**
     * 管理员周报
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/week/export")
    public void adminWeekExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        List<AdminWeekVo> adminWeekVos = reportService.exportAdminWeek(currVo.getDeptId(), date);

        String fileTitle = "资产管理部" + date + "周报";
        String fileName = fileTitle;
        AdminWeekReportExport export = new AdminWeekReportExport(fileName, fileTitle, adminWeekVos);
        export.exportExcel(request, response);
    }

    /**
     * 管理员月报
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/month")
    public String adminMonth(HttpServletRequest request, Page page, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = reportService.findAdminMonth(page, currVo.getDeptId(), date);

        model.addAttribute("page", page);
        return "/followup/uc/log/mydaily";
    }


}
