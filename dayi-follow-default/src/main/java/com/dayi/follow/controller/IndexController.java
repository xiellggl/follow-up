package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.vo.IndexVo;
import com.dayi.follow.vo.LoginVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("")
    public String index(HttpServletRequest request) {
        LoginVo currVo = userComponent.getCurrUser(request);
        return "uc/index";
    }

    @RequestMapping("sale/personal/daily")
    @ResponseBody
    public BizResult salePersonalDaily(HttpServletRequest request) {
        //判断权限
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
        //判断权限
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null) {
            return BizResult.FAIL;
        }
        IndexVo daily = countService.countPreDayDaily(null, currVo.getDeptId());//日报
        return BizResult.succ(daily);
    }
//
//    @RequestMapping("sale/team/daily")
//    public String saleTeamDaily(HttpServletRequest request, Model model) {
//        LoginVo currVo = userComponent.getCurrUser(request);
//        boolean isKA = currVo.getDeptName().equals("KA及渠道部") ? true : false;
//        boolean isManager = currVo.getIsManager().equals(1) ? true : false;
//        String followId = currVo.getId();  // 跟进人ID
//        IndexVo vo = new IndexVo();
//        List<IndexVo> pDDailyList = null;
//        if (isManager) {//是否负责人
//            pDDailyList = flowUpService.countPreDayDaily(null, currVo.getDeptId());//负责人日报
//        } else {
//            pDDailyList = flowUpService.countPreDayDaily(flowId, null);//日报
//        }
//        if (pDDailyList.size() != 0) {
//            pDDaily = pDDailyList.get(0);
//        }
//        if (pDDaily.getDate() != null) {
//            model.addAttribute("pDDaily", pDDaily);//日报
//        }
//        return null;
//    }
//
//
//    /**
//     * 用户首页
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping("/sale/daily")
//    public String index1(HttpServletRequest request, Model model) {
//        LoginVo currVo = userComponent.getCurrUser(request);
//        if (currVo == null) {
//            return "redirect:/followup/login";
//        }
//        if ((currVo.getIsAdmin().equals(1)) || ("admin".equals(currVo.getUserName()))) {
//            return "redirect:/followup/manage/index";
//        }
//        boolean isKA = currVo.getDeptName().equals("KA及渠道部") ? true : false;
//        boolean isManager = currVo.getIsManager().equals(1) ? true : false;
//        String followId = currVo.getId();  // 跟进人ID
//        IndexVo pDDaily = new IndexVo();
//        List<IndexVo> pDDailyList = null;
//        if (isManager) {//是否负责人
//            pDDailyList = flowUpService.countPreDayDaily(null, String.valueOf(vo.getDeptId()));//负责人日报
//        } else {
//            pDDailyList = flowUpService.countPreDayDaily(flowId, null);//日报
//        }
//        if (pDDailyList.size() != 0) {
//            pDDaily = pDDailyList.get(0);
//        }
//        if (pDDaily.getDate() != null) {
//            model.addAttribute("pDDaily", pDDaily);//日报
//        }
//        FollowUpCountVo fUCountVo = financeAgentService.getTotal(flowId, null, null, null);//客户状态
//        model.addAttribute("fUCountVo", fUCountVo);//客户状态
//        if (isKA) { //创客数据
//            List<Integer> deptIdList = new ArrayList<Integer>();
//            deptIdList.add(vo.getDeptId());
//            IndexVo orgCount;
//            if (isManager) {
//                orgCount = flowUpService.indexOrgCount(null, null, null, deptIdList);
//            } else {
//                orgCount = flowUpService.indexOrgCount(flowId, null, null, null);
//            }
//            model.addAttribute("orgCount", orgCount);//创客数据
//        } else {
//            IndexVo wLCount = flowUpService.indexWaitLinkCount(flowId, new Date());//待联系代理商
//            model.addAttribute("wLCount", wLCount);//待联系代理商
//        }
//        model.addAttribute("isKA", isKA);
//        model.addAttribute("isManager", isManager);
//        return "/followup/uc/index";
//    }
//
//    /**
//     * 统计图形
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/charts")
//    @ResponseBody
//    public BizRetVo charts(HttpServletRequest request) {
//        BizRetVo retVo = new BizRetVo();
//        FlowUpLoginVo vo = financeUserComponent.getCurrentLoginFollowUp(request);
//        if (vo == null || (vo.getIsAdmin().equals(1)) || ("admin".equals(vo.getUserName()))) {
//            retVo.setError("无权限");
//            return retVo;
//        }
//        boolean isKA = vo.getDeptName().equals("KA及渠道部") ? true : false;
//        Integer flowId = vo.getId();  // 跟进人ID
//        Map<String, List<IndexVo>> map = new LinkedHashMap<String, List<IndexVo>>();
//        List<IndexVo> cusTypeList = flowUpService.countCustomerType(flowId);//客户类型占比
//        for (IndexVo indexVo : cusTypeList) {//遍历--数字转中文
//            Agent.CustomerType[] values = Agent.CustomerType.values();
//            for (Agent.CustomerType value : values) {
//                String str = value.getValue().toString();
//                String customerType = indexVo.getCustomerType();
//                if (customerType != null && str.equals(customerType)) {
//                    indexVo.setCustomerType(value.getName());
//                }
//            }
//        }
//        List<IndexVo> totalMoneysList = flowUpService.countTotalMoney(flowId);//总资产各阶级占比
//        for (IndexVo indexVo : totalMoneysList) {//遍历--数字转中文
//            Agent.RankAsset[] values = Agent.RankAsset.values();
//            for (Agent.RankAsset value : values) {
//                String str = value.getValue().toString();
//                if (str.equals(indexVo.getMoneyType())) {
//                    indexVo.setMoneyType(value.getName());
//                }
//            }
//        }
//        List<IndexVo> sDInCashList = flowUpService.nearSevenDayInCash(flowId);//近七日入金总额
//        map.put("cusTypeList", cusTypeList);//客户类型占比
//        map.put("totalMoneysList", totalMoneysList);//总资产各阶级占比
//        map.put("sDInCashList", sDInCashList);//近七日入金总额
//        if (!isKA) {
//            List<IndexVo> sDOpenList = flowUpService.nearSevenDayOpen(flowId);//近七日开户数
//            map.put("sDOpenList", sDOpenList);//近七日开户数
//        }
//        retVo.setSuccess("操作成功");
//        retVo.setItem(map);
//        return retVo;
//    }
//
//    /**
//     * 我的资料 -- 查询
//     */
//    @RequestMapping("/myinfo")
//    public String myInfo(HttpServletRequest request, Model model) {
//        FlowUpLoginVo vo = financeUserComponent.getCurrentLoginFollowUp(request);
//        FlowUp flowUp = flowUpService.getFlowUp(vo.getId());
//        Date createDate = flowUp.getCreateDate();
//        Date modifyDate = flowUp.getModifyDate();
//        String createDateStr = createDate.toString();
//        String modifyDateStr = modifyDate.toString();
//        String createDateStrSub = createDateStr.substring(0, createDateStr.length() - 2);
//        String modifyDateStrSub = modifyDateStr.substring(0, modifyDateStr.length() - 2);
//        model.addAttribute("followUp", flowUp);
//        model.addAttribute("createDateStrSub", createDateStrSub);
//        model.addAttribute("modifyDateStrSub", modifyDateStrSub);
//        return "/followup/uc/myinfo";
//    }
//
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
