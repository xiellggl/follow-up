package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.DateUtil;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

    @RequestMapping("")
    public String index(HttpServletRequest request) {
        return "uc/index";
    }

    @RequestMapping("sale/personal/daily")
    @ResponseBody
    public BizResult salePersonalDaily(HttpServletRequest request) {
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        IndexVo daily = countService.countPreDayDaily(currVo.getId(), null);//日报
        return BizResult.succ(daily);
    }

    @RequestMapping("sale/team/daily")
    public BizResult saleTeamDaily(HttpServletRequest request) {
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        IndexVo daily = countService.countPreDayDaily(null, currVo.getDeptId());//负责人日报
        return BizResult.succ(daily);
    }

    @RequestMapping("ka/personal/daily")
    @ResponseBody
    public BizResult kaPersonalDaily(HttpServletRequest request) {
        //判断权限
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        IndexVo daily = countService.countPreDayDaily(currVo.getId(), null);//日报
        return BizResult.succ(daily);
    }

    @RequestMapping("ka/team/daily")
    @ResponseBody
    public BizResult kaTeamDaily(HttpServletRequest request) {
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        IndexVo daily = countService.countPreDayDaily(null, currVo.getDeptId());//日报
        return BizResult.succ(daily);
    }

    @RequestMapping("cus/status")
    @ResponseBody
    public BizResult cusStatus(HttpServletRequest request) {
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        CusStatusVo cusStatus = countService.countCusStatus(currVo.getId());//客户状态
        return BizResult.succ(cusStatus);
    }

    @RequestMapping("agent/link")
    @ResponseBody
    public BizResult agentLink(HttpServletRequest request) {
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        String dateStr = DateUtil.formatDate(new Date());
        Integer num = countService.getAgentNumWait2Link(currVo.getId(), dateStr);//待联系代理商
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


    /**
     * 我的资料 -- 查询
     */
    @RequestMapping("/myinfo")
    public String myInfo(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);
        FollowUp followUp = followUpService.get(currVo.getId());
//        Date createDate = flowUp.getCreateDate();
//        Date modifyDate = flowUp.getModifyDate();
//        String createDateStr = createDate.toString();
//        String modifyDateStr = modifyDate.toString();
//        String createDateStrSub = createDateStr.substring(0, createDateStr.length() - 2);
//        String modifyDateStrSub = modifyDateStr.substring(0, modifyDateStr.length() - 2);
        model.addAttribute("followUp", followUp);
//        model.addAttribute("createDateStrSub", createDateStrSub);
//        model.addAttribute("modifyDateStrSub", modifyDateStrSub);
        return "/followup/uc/myinfo";
    }

//    /**
//     * 我的资料 -- 查询
//     */
//    @RequestMapping("/pwd")
//    public String pwd() {
//        return "/followup/uc/pwd";
//    }
//
//    /**
//     * 修改 -- 修改密码
//     */
//    @RequestMapping(value = "/update/pwd")
//    @ResponseBody
//    public BizRetVo updateLoginPassword(HttpServletRequest request,
//                                        @RequestParam(value = "oldPwd", required = true) String oldPwd,
//                                        @RequestParam(value = "newPwd", required = true) String newPwd,
//                                        @RequestParam(value = "confirmNewPwd", required = true) String confirmNewPwd) {
//        FlowUpLoginVo vo = financeUserComponent.getCurrentLoginFollowUp(request);
//        BizRetVo retVo = flowUpService.updatePwdById(vo.getId(), oldPwd, newPwd, confirmNewPwd);
//        if (retVo.isSucc()) retVo.setMsg("修改密码成功");
//        return retVo;
//    }


}
