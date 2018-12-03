package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.DateUtil;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.service.*;
import com.dayi.follow.vo.*;
import com.dayi.follow.vo.index.FundRankVo;
import com.dayi.follow.vo.index.SerCusStatusVo;
import com.dayi.follow.vo.index.SevenInCashVo;
import com.dayi.follow.vo.index.SevenOpenVo;
import com.dayi.follow.vo.org.OrgDataVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Resource
    FollowUpService followUpService;
    @Resource
    CountService countService;
    @Resource
    UserComponent userComponent;
    @Resource
    AgentService agentService;
    @Resource
    DeptService deptService;
    @Resource
    ReportService reportService;
    @Resource
    OrgService orgService;

    @RequestMapping("")
    public String index(HttpServletRequest request) {
        return "uc/index";
    }

    @RequestMapping("sale/personal/daily")
    @ResponseBody
    public BizResult salePersonalDaily(HttpServletRequest request) {//销售个人日报
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        DailyVo daily = reportService.countDaily(currVo.getId());
        return BizResult.succ(daily);
    }

    @RequestMapping("sale/team/daily")
    public BizResult saleTeamDaily(HttpServletRequest request) {//销售团队日报
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        List<DailyVo> dailyVos = reportService.countTeamDaily(currVo.getDeptId());
        return BizResult.succ(dailyVos);
    }

    @RequestMapping("ka/personal/daily")
    @ResponseBody
    public BizResult kaPersonalDaily(HttpServletRequest request) {//ka个人日报
        //判断权限
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        DailyVo daily = reportService.countDaily(currVo.getId());
        return BizResult.succ(daily);
    }

    @RequestMapping("ka/team/daily")
    @ResponseBody
    public BizResult kaTeamDaily(HttpServletRequest request) {//ka团队日报
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        List<DailyVo> dailyVos = reportService.countTeamDaily(currVo.getDeptId());
        return BizResult.succ(dailyVos);
    }

    @RequestMapping("cus/status")
    @ResponseBody
    public BizResult cusStatus(HttpServletRequest request) {//销售客户状态
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        CusStatusVo cusStatus = countService.countCusStatus(currVo.getId());
        return BizResult.succ(cusStatus);
    }

    @RequestMapping("agent/link")
    @ResponseBody
    public BizResult agentLink(HttpServletRequest request) {//待联系代理商
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        String dateStr = DateUtil.formatDate(new Date());
        long num = countService.getAgentNumWait2Link(currVo.getId(), dateStr);
        return BizResult.succ(num);
    }

    @RequestMapping("cus/proportion")
    @ResponseBody
    public BizResult cusProportion(HttpServletRequest request) {//客户类型占比
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        List<CusTypeRatioVo> cusTypeRatioVos = countService.countCusTypeRatio(currVo.getId());
        return BizResult.succ(cusTypeRatioVos);
    }


    @RequestMapping("cus/fund/rank")
    @ResponseBody
    public BizResult cusFundRank(HttpServletRequest request) {//客户资产阶级统计
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        List<FundRankVo> fundRankVos = countService.countCusFundRank(currVo.getId());
        return BizResult.succ(fundRankVos);
    }

    @RequestMapping("seven/open")
    @ResponseBody
    public BizResult sevenOpen(HttpServletRequest request) {//统计近七日开户数
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        List<SevenOpenVo> sevenOpenVos = countService.countSevenOpen(currVo.getId());
        return BizResult.succ(sevenOpenVos);
    }

    @RequestMapping("seven/incash")
    @ResponseBody
    public BizResult sevenInCash(HttpServletRequest request) {//统计近七日入金
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        List<SevenInCashVo> sevenOpenVos = countService.countSevenInCash(currVo.getId());
        return BizResult.succ(sevenOpenVos);
    }

    @RequestMapping("ser/cus/status")
    @ResponseBody
    public BizResult serCusStatus(HttpServletRequest request) {//客服的客户状态,即原来系统的管理员
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }

        //获取下级部门
        List<String> deptIds = deptService.getSubDeptIds(currVo.getDeptId());

        SerCusStatusVo serCusStatus = countService.countSerCusStatus(deptIds);
        return BizResult.succ(serCusStatus);
    }

    @RequestMapping("ka/personal/orgdata")
    @ResponseBody
    public BizResult kaPerOrgData(HttpServletRequest request) {//KA个人创客数据
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        OrgDataVo orgDataVo = countService.countOrgData(currVo.getId());
        return BizResult.succ(orgDataVo);
    }

    @RequestMapping("ka/team/orgdata")
    @ResponseBody
    public BizResult kaTeamOrgData(HttpServletRequest request) {//KA团队创客数据
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        OrgDataVo orgDataVo = countService.countTeamOrgData(currVo.getDeptId());
        return BizResult.succ(orgDataVo);
    }





}
