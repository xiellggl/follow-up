package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.DateUtil;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.*;
import com.dayi.follow.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/followup/index")
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
        DailyVo daily = reportService.countTeamDaily(currVo.getDeptId());
        return BizResult.succ(daily);
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
        DailyVo daily = reportService.countTeamDaily(currVo.getDeptId());
        return BizResult.succ(daily);
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
        String chargeDeptId = currVo.getChargeDeptId();
        if (StringUtils.isBlank(chargeDeptId)) return BizResult.SUCCESS;
        String[] roleArr = chargeDeptId.split(",");
        SerCusStatusVo serCusStatus = countService.countSerCusStatus(Arrays.asList(roleArr));
        return BizResult.succ(serCusStatus);
    }

    @RequestMapping("ser/team/daily")
    @ResponseBody
    public BizResult serTeamDaily(HttpServletRequest request) {//客服的团队日报
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        String chargeDeptId = currVo.getChargeDeptId();
        if (StringUtils.isBlank(chargeDeptId)) return BizResult.SUCCESS;
        String[] roleArr = chargeDeptId.split(",");
        Map map = reportService.countSerTeamDaily(Arrays.asList(roleArr));
        return BizResult.succ(map);
    }


    /**
     * 我的资料 -- 查询
     */
    @RequestMapping("/myinfo")
    public String myInfo(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);

        FollowUp followUp = followUpService.get(currVo.getId());

        Department department = deptService.get(currVo.getDeptId());

        FollowUpVo followUpVo = new FollowUpVo();
        followUpVo.setName(followUp.getName());
        followUpVo.setUserName(followUp.getUserName());

        if (department != null) {
            followUpVo.setDepartment(department);
            followUpVo.setDeptName(department.getName());
        }

        followUpVo.setInviteCode(followUp.getInviteCode());
        followUpVo.setIsAdmin(followUp.getIsAdmin());
        followUpVo.setIsManager(followUp.getIsManager());
        followUpVo.setCreateTimeFm(DateUtil.formatDate(followUp.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        followUpVo.setUpdateTimeFm(DateUtil.formatDate(followUp.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));

        model.addAttribute("followUp", followUpVo);
        return "uc/myinfo";
    }

    /**
     * 我的资料 -- 查询
     */
    @RequestMapping("/pwd")
    public String pwd() {
        return "uc/pwd";
    }

    /**
     * 修改 -- 修改密码
     */
    @RequestMapping(value = "/update/pwd")
    @ResponseBody
    public BizResult updatePassword(HttpServletRequest request,
                                    @RequestParam(value = "oldPwd", required = true) String oldPwd,
                                    @RequestParam(value = "newPwd", required = true) String newPwd,
                                    @RequestParam(value = "confirmNewPwd", required = true) String confirmNewPwd) {
        LoginVo currVo = userComponent.getCurrUser(request);
        return followUpService.editPwd(currVo.getId(), oldPwd, newPwd, confirmNewPwd);
    }


}
