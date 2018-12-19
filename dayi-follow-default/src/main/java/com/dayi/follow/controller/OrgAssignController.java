package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.enums.OrgTypeEnum;
import com.dayi.follow.model.follow.*;
import com.dayi.follow.service.*;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/org/assign")
public class OrgAssignController extends BaseController {
    @Resource
    FollowUpService followUpService;
    @Resource
    UserComponent userComponent;
    @Resource
    FollowOrgService followOrgService;
    @Resource
    OrgService orgService;
    @Resource
    FollowAgentService followAgentService;
    @Resource
    AgentService agentService;

    /**
     * 创客分配列表
     */
    @RequestMapping("/list")
    public String assignList(HttpServletRequest request, Model model, Page page, SearchVo searchVo) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        page = followOrgService.findAssignPage(page, searchVo, currVo.getDeptId());

        request.setAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("orgTypes", OrgTypeEnum.values());//机构商类型
        return "/followup/manage/followuper/assign_list";
    }


    /**
     * 跟进人分配 -- 创客 -- 分配保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public BizResult assignSave(HttpServletRequest request, @Valid FollowOrg followOrg, BindingResult result) {
        BizResult bizResult = checkErrors(result);

        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        return followOrgService.add(followOrg);
    }

    /**
     * 跟进人分配 --创客 -- 清除分配
     */
    @RequestMapping("/clear")
    @ResponseBody
    public BizResult assignClear(HttpServletRequest request, @RequestParam Integer id) {
        FollowOrg followOrg = followOrgService.getFollowOrgByOrgId(id);

        if (followOrg == null) return BizResult.FAIL;

        return followOrgService.clear(followOrg);
    }

    /**
     * 跟进人分配 -- 创客 -- 分配保存
     */
    @RequestMapping("/save/batch")
    @ResponseBody
    public BizResult assignSave(HttpServletRequest request, @RequestParam("orgIds") String orgIds, @RequestParam("followId") String followId) {
        List<FollowOrg> followOrgs = new ArrayList<FollowOrg>();

        String[] split = StringUtils.split(orgIds, ",");
        for (String s : split) {
            Organization org = orgService.get(Integer.valueOf(s));
            if (org == null) return BizResult.FAIL;

            FollowOrg followOrg = new FollowOrg();
            followOrg.setOrgId(Integer.valueOf(s));
            followOrg.setFollowId(followId);

            followOrgs.add(followOrg);
        }
        return followOrgService.addBatch(followOrgs);

    }


    /**
     * 跟进人分配 -- 创客  -- 清除分配
     */
    @RequestMapping("/clear/batch")
    @ResponseBody
    public BizResult assignClear(HttpServletRequest request, @RequestParam String ids) {
        List<FollowOrg> followOrgs = new ArrayList<FollowOrg>();

        String[] split = StringUtils.split(ids, ",");
        for (String s : split) {
            FollowOrg followOrg = followOrgService.getFollowOrgByOrgId(Integer.valueOf(s));
            if (followOrg == null) return BizResult.FAIL;
            followOrgs.add(followOrg);
        }
        return followOrgService.clearBatch(followOrgs);
    }


}