package com.dayi.follow.controller;

import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.export.AgentDetailExport;
import com.dayi.follow.vo.export.MakerDetailExport;
import com.dayi.follow.vo.followup.FMDetailListVo;
import com.dayi.mybatis.support.Page;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        String name = request.getParameter("name");
        String queryDeptId = request.getParameter("deptId");
        String inviteCode = request.getParameter("inviteCode");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = followUpService.findPage(page, name, queryDeptId, inviteCode);

        List<Department> deptList = deptService.getDeptTree(null);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("deptList", deptList);  // 所属部门下拉选择数据
        model.addAttribute("queryDeptId", queryDeptId);  // 查询条件 -- 所属部门
        return "followup/list";
    }


    @RequestMapping(value = "/agent/list")
    public String agentList(HttpServletRequest request, Model model, SearchVo searchVo, Page page) {
        String followId = request.getParameter("followId");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = followUpService.findAgentPage(page, searchVo, followId);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("page", page);
        model.addAttribute("pageUrl", pageUrl);
        return "/followup/detail/agent_list";
    }


    @RequestMapping(value = "/all/agent/list")
    public String teamAgentList(HttpServletRequest request, Model model, SearchVo searchVo, Page page) {

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = followUpService.findAllAgentPage(page, searchVo);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("page", page);
        model.addAttribute("pageUrl", pageUrl);
        return "/followup/detail/agent_list";
    }


    @RequestMapping(value = "/org/list")
    public String orgList(HttpServletRequest request, Model model, SearchVo searchVo, Page page) {
        String followId = request.getParameter("followId");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = followUpService.findOrgPage(page, searchVo, followId);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("page", page);
        model.addAttribute("pageUrl", pageUrl);
        return "/followup/detail/maker_list";
    }

    @RequestMapping(value = "/all/org/list")
    public String teamOrgList(HttpServletRequest request, Model model, SearchVo searchVo, Page page) {

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = followUpService.findAllOrgPage(page, searchVo);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("page", page);
        model.addAttribute("pageUrl", pageUrl);
        return "/followup/detail/maker_list";
    }


    @RequestMapping(value = "/org/list/export")
    @ResponseBody
    public void orgExport(HttpServletRequest request, HttpServletResponse response, SearchVo searchVo) throws IOException {
        String followId = request.getParameter("followId");

        List<FMDetailListVo> orgList = followUpService.findOrgList(searchVo, followId);
        String title = "跟进创客明细";
        String fileName = title + new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        MakerDetailExport export = new MakerDetailExport(fileName, title, orgList);
        export.exportExcel(request, response);
    }

    @RequestMapping(value = "/all/org/list/export")
    @ResponseBody
    public void allOrgExport(HttpServletRequest request, HttpServletResponse response, SearchVo searchVo) throws IOException {

        List<FMDetailListVo> allOrgList = followUpService.findAllOrgList(searchVo);

        String title = "跟进创客明细";
        String fileName = title + new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        MakerDetailExport export = new MakerDetailExport(fileName, title, allOrgList);
        export.exportExcel(request, response);
    }

    @RequestMapping(value = "/agent/list/export")
    @ResponseBody
    public void agentExport(HttpServletRequest request, HttpServletResponse response, SearchVo searchVo) throws IOException {
        String followId = request.getParameter("followId");

        List<FMDetailListVo> agentList = followUpService.findAgentList(searchVo, followId);
        String title = "跟进代理商明细";
        String fileName = title + new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        AgentDetailExport export = new AgentDetailExport(fileName, title,agentList);
        export.exportExcel(request, response);

    }

    @RequestMapping(value = "/all/agent/list/export")
    @ResponseBody
    public void allAgentExport(HttpServletRequest request, HttpServletResponse response, SearchVo searchVo) throws IOException {
        LoginVo currVo = userComponent.getCurrUser(request);

        List<FMDetailListVo> allAgentList = followUpService.findAllAgentList(searchVo);

        String title = "跟进代理商明细";
        String fileName = title + new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        AgentDetailExport export = new AgentDetailExport(fileName, title, allAgentList);
        export.exportExcel(request, response);
    }


}
