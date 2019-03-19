package com.dayi.follow.controller;

import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.SourceReport;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.service.ReportService;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.export.AdminMonthExport;
import com.dayi.follow.vo.export.AdminWeekExport;
import com.dayi.follow.vo.export.TeamDailyDetailExport;
import com.dayi.follow.vo.report.*;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import java.io.IOException;
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
        return "/followup/daily/daily_list";
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

        page = reportService.findTeamDaily(page, currVo.getDeptId(), date);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("date", date);
        return "/followup/daily/team_daily_list";
    }

    /**
     * 团队日报详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/team/daily/detail")
    public String teamDailyDetail(HttpServletRequest request, Page page, Model model) {

        LoginVo currUser = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = reportService.findTeamDailyDetail(page, currUser.getDeptId(), date);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("date", date);
        model.addAttribute("deptName", currUser.getDeptName());
        return "/followup/daily/team_daily_detail";
    }

    /**
     * 团队日报详情导出
     *
     * @param request
     * @return
     */
    @RequestMapping("/team/daily/detail/export")
    public void teamDetailExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginVo currVo = userComponent.getCurrUser(request);
        String date = request.getParameter("date");

        List<ReportDailyVo> reportDailyVos = reportService.exportTeamDailyDetail(currVo.getDeptId(), date);

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

        MyWeekVo weekVo = reportService.getWeek(currVo.getId(), date);

        model.addAttribute("weekVo", weekVo);
        return "/followup/week/week_list";
    }

    /**
     * 团队周报
     *
     * @param request
     * @return
     */
    @RequestMapping("/team/week")
    public String teamWeek(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        TeamWeekVo teamWeekVo = reportService.countTeamWeek(currVo.getDeptId(), date);

        model.addAttribute("teamWeekVo", teamWeekVo);
        return "/followup/week/team_week_list";
    }

    /**
     * 月报
     *
     * @param request
     * @return
     */
    @RequestMapping("/month")
    public String month(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        MyMonthVo monthVo = reportService.getMonth(currVo.getId(), date);

        model.addAttribute("monthVo", monthVo);
        return "/followup/month/month_list";
    }

    /**
     * 团队月报
     *
     * @param request
     * @return
     */
    @RequestMapping("/team/month")
    public String teamMonth(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        TeamMonthVo teamMonthVo = reportService.countTeamMonth(currVo.getDeptId(), date);

        model.addAttribute("teamMonthVo", teamMonthVo);
        return "/followup/month/team_month_list";
    }

    /**
     * 管理员日报
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/daily")
    public String adminDaily(HttpServletRequest request, Model model) {

        String date = request.getParameter("date");

        List list = reportService.findAdminDaily(date);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("list", list);
        request.setAttribute("pageUrl", pageUrl);
        return "/followup/daily/admin_daily_list";
    }

    /**
     * 管理员日报详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/daily/detail")
    public String adminDailyDetail(HttpServletRequest request, Page page, Model model) {
        String date = request.getParameter("date");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        List list = reportService.findAdminDailyDetail(date);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("list", list);
        model.addAttribute("date", date);
        return "followup/daily/admin_daily_detail";
    }

    /**
     * 管理员日报详情导出
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/daily/detail/export")
    public void adminDailyExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginVo currVo = userComponent.getCurrUser(request);
        String deptId = request.getParameter("deptId");
        String date = request.getParameter("date");

        List list = reportService.findAdminDailyDetailList(deptId, date);

        String fileTitle = "团队日报详情";
        String fileName = currVo.getName() + "-" + fileTitle + new DateTime().toString("yyyy-MM-dd HH:mm:ss");

        TeamDailyDetailExport export = new TeamDailyDetailExport(fileName, fileTitle, list);
        export.exportExcel(request, response);
    }

    /**
     * 管理员周报
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/week")
    public String adminWeek(HttpServletRequest request, Page page, Model model) {
        String date = request.getParameter("date");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        AdminWeekVo adminWeekVo = reportService.countAdminWeek(page, date);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("adminWeekVo", adminWeekVo);
        return "/followup/week/admin_week_list";
    }

    /**
     * 管理员周报导出
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/week/export")
    public void adminWeekExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = request.getParameter("date");

        if (StringUtils.isBlank(date)) {
            String one = DateTime.now().plusWeeks(-1).withDayOfWeek(1).toString("yyyy-MM-dd");
            String five = DateTime.now().plusWeeks(-1).withDayOfWeek(5).toString("yyyy-MM-dd");
            date = one + " - " + five;
        }

        String[] split = date.split(" - ");
        String fileTitle = "资产管理部" + split[0] + "至" + split[1] + "周报";

        List<WeekVo> adminWeekList = reportService.findAdminWeekList(date);

        String fileName = fileTitle;
        AdminWeekExport export = new AdminWeekExport(fileName, fileTitle, adminWeekList);
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
        String date = request.getParameter("date");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        AdminMonthVo adminMonthVo = reportService.countAdminMonth(page, date);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("adminMonthVo", adminMonthVo);
        request.setAttribute("pageUrl", pageUrl);
        return "/followup/month/admin_month_list";
    }


    /**
     * 管理员月报导出
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/month/export")
    public void adminMonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginVo currVo = userComponent.getCurrUser(request);

        String date = request.getParameter("date");

        if (StringUtils.isBlank(date)) date = DateTime.now().plusMonths(-1).toString("yyyy-MM");

        List<MonthVo> adminMonthVos = reportService.findAdminMonthList(currVo.getDeptId(), date);

        String fileTitle = "资产管理部" + date + "月报";
        String fileName = fileTitle;
        AdminMonthExport export = new AdminMonthExport(fileName, fileTitle, adminMonthVos);
        export.exportExcel(request, response);

    }

}
