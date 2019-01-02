package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.DateUtil;
import com.dayi.common.web.util.IPUtil;
import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowOrgMapper;
import com.dayi.follow.dao.follow.FollowUpLogMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.enums.MemberStatusEnum;
import com.dayi.follow.model.follow.*;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.RoleService;
import com.dayi.follow.service.UserService;
import com.dayi.follow.util.Md5Util;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.user.UserEditDto;
import com.dayi.follow.vo.user.UserVo;
import com.dayi.follow.vo.LoginVo;
import com.dayi.mybatis.support.Page;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.support.UsernamePasswordToken;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    UserComponent userComponent;
    @Resource
    UserService userService;
    @Resource
    DeptService deptService;
    @Resource
    FollowUpMapper followUpMapper;
    @Resource
    FollowAgentMapper followAgentMapper;
    @Resource
    FollowUpLogMapper followUpLogMapper;
    @Resource
    FollowOrgMapper followOrgMapper;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;
    @Resource
    CountService countService;

    @Resource
    RoleService roleService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) {
        return "login";
    }


    /**
     * 登录
     */
    @RequestMapping("/login/post")
    @ResponseBody
    public BizResult login(HttpServletRequest request, @Valid @ModelAttribute("loginVo") LoginVo loginVo, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) {
            return bizResult;//参数传入错误
        } else {
            boolean b = AuthorizationManager.login(request, new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword(), IPUtil.getIp(request)));
            if (b) return userService.login(loginVo);
            else return BizResult.fail("账号密码错误！");
        }
    }


    @RequestMapping("loginout")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
        AuthorizationManager.cleanAllAuthenticationInfo(request, response);
        return "redirect:/user/login";
    }

    @RequestMapping(value = "/list")
    public String list(HttpServletRequest request, Model model, Page page) {
        //LoginVo currVo = userComponent.getCurrUser(request);

        String name = request.getParameter("name");
        String queryDeptId = request.getParameter("deptId");
        String inviteCode = request.getParameter("inviteCode");

        page = userService.findPage(page, name, queryDeptId, inviteCode);

        List<Department> deptList = deptService.getDeptTree(null);//前端要求-用于新增修改的部门列表

        List<Role> rolesList = roleService.listAll(true);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        model.addAttribute("deptList", deptList);
        model.addAttribute("rolesList", rolesList);

        return "sys/user/list";
    }


    /**
     * 新增 -- 保存 -- 用户
     */
    @RequestMapping("/add/save")
    @ResponseBody
    public BizResult add(HttpServletRequest request, @Valid FollowUp followUp, BindingResult result) {
        BizResult bizResult = checkErrors(result);

        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        LoginVo currVo = userComponent.getCurrUser(request);

        if (userService.getByUserName(followUp.getUserName()) != null) {
            return BizResult.fail("用户名已存在！");
        }

        if (!userService.checkCodeRepeat(followUp.getInviteCode())) {
            return BizResult.fail("邀请码已存在！");
        }

        followUp.setPassword(Md5Util.md5(followUp.getUserName(), followUp.getPassword()));
        followUp.setCreateBy(currVo.getName());
        followUp.setUpdateBy(currVo.getName());
        return userService.add(followUp);


    }


    /**
     * 校验用户名
     */
    @RequestMapping("/check/userName")
    @ResponseBody
    public boolean checkUserName(String userName) {
        if (userService.getByUserName(userName) == null) return true;
        return false;
    }

    /**
     * 校验邀请码
     */
    @RequestMapping("/check/inviteCode")
    @ResponseBody
    public boolean checkInviteCode(String inviteCode) {
        return userService.checkCodeRepeat(inviteCode);
    }


    /**
     * 修改 -- 保存 -- 用户
     */
    @RequestMapping("/update/save")
    @ResponseBody
    public BizResult updateSave(HttpServletRequest request, @Valid UserEditDto userEditDto, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        LoginVo currVo = userComponent.getCurrUser(request);
        userEditDto.setUpdateBy(currVo.getName());
        userEditDto.setUpdateTime(new Date());
        return userService.update(userEditDto);


    }

    /**
     * 获取用户信息
     */
    @RequestMapping("/get")
    @ResponseBody
    public BizResult getUserInfo(HttpServletRequest request, String id) {
        FollowUp followUp = userService.get(id);
        return BizResult.succ(followUp, "操作成功！");
    }

    /**
     * 禁用 -- 用户
     */
    @RequestMapping(value = "/disable")
    @ResponseBody
    public BizResult disable(HttpServletRequest request, String id) {
        if (StringUtils.isBlank(id)) return BizResult.FAIL;
        LoginVo currVo = userComponent.getCurrUser(request);

        FollowUp followUp = userService.get(id);
        if (followUp == null) return BizResult.fail("用户不存在.");

        followUp.setUpdateBy(currVo.getName());
        return userService.disable(followUp);
    }

    /**
     * 启用 -- 用户
     */
    @RequestMapping(value = "/enable")
    @ResponseBody
    public BizResult enable(HttpServletRequest request, String id) {
        if (StringUtils.isBlank(id)) return BizResult.FAIL;
        LoginVo currVo = userComponent.getCurrUser(request);

        FollowUp followUp = userService.get(id);
        if (followUp == null) return BizResult.fail("用户不存在.");

        followUp.setUpdateBy(currVo.getName());
        return userService.enable(followUp);
    }

    /**
     * 重置密码 -- 用户
     */
    @RequestMapping(value = "/resetpwd")
    @ResponseBody
    public BizResult manageResetPwd(String id, @RequestParam("pwd") String pwd, HttpServletRequest request) {
        if (StringUtils.isBlank(id)) return BizResult.FAIL;
        LoginVo currVo = userComponent.getCurrUser(request);

        if (StringUtils.isBlank(pwd)) return BizResult.fail("密码不能为空!");

        FollowUp followUp = userService.get(id);
        if (followUp == null) return BizResult.FAIL;

        followUp.setUpdateBy(currVo.getName());

        return userService.resetPwd(followUp, pwd);
    }

    /**
     * 删除 -- 用户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public BizResult delete(HttpServletRequest request, String id) {
        if (StringUtils.isBlank(id)) return BizResult.FAIL;
        FollowUp followUp = userService.get(id);
        if (followUp == null) return BizResult.fail("用户不存在.");
        return userService.delete(followUp);
    }

    /**
     * 我的资料 -- 查询
     */
    @RequestMapping("/myinfo")
    public String myInfo(HttpServletRequest request, Model model) {
        LoginVo currVo = userComponent.getCurrUser(request);
        UserVo userVo = new UserVo();

        FollowUp followUp = userService.get(currVo.getId());
        if (followUp != null) {
            Department department = deptService.getDept(currVo.getDeptId());

            userVo.setName(followUp.getName());
            userVo.setUserName(followUp.getUserName());

            if (department != null) {
                userVo.setDepartment(department);
                userVo.setDeptName(department.getName());
            }

            userVo.setInviteCode(followUp.getInviteCode());
            userVo.setCreateTimeFm(DateUtil.formatDate(followUp.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            userVo.setUpdateTimeFm(DateUtil.formatDate(followUp.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));

            //处理角色名称
            if (!StringUtils.isBlank(followUp.getRoleids())) {
                String[] split = StringUtils.split(followUp.getRoleids(), ",");
                for (String roleId : split) {
                    Role role = roleService.getRole(roleId);
                    if (role == null) break;
                    userVo.setRolesName(role.getName() + ",");
                }
                //处理最后一个,
                if (!StringUtils.isBlank(userVo.getRolesName())) {
                    userVo.setRolesName(userVo.getRolesName().substring(0, userVo.getRolesName().length() - 1));
                }
            }
        }

        model.addAttribute("user", userVo);
        return "/myinfo";
    }

    /**
     * 我的资料 -- 查询
     */
    @RequestMapping("/pwd")
    public String pwd() {
        return "/pwd";
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
        return userService.editPwd(currVo.getId(), oldPwd, newPwd, confirmNewPwd);
    }

    /**
     * 便于测试，生成销售统计，上线删除
     */
    @RequestMapping("/test/log")
    public BizResult test(Date date) {
        if (date == null) return BizResult.FAIL;
        List<FollowUp> followUps = followUpMapper.findAll();
        for (FollowUp followUp : followUps) {
            //统计昨天23:30:01到今天23:30:00的数据
            String stratTime = new DateTime(date).plusDays(-1).millisOfDay().withMaximumValue()
                    .plusMinutes(-30).plusSeconds(2).toString("yyyy-MM-dd HH:mm:ss");

            String endTime = new DateTime(date).plusDays(1).millisOfDay().withMinimumValue()
                    .plusMinutes(-30).toString("yyyy-MM-dd HH:mm:ss");

            FollowUpLog followUpLog = new FollowUpLog();

            int openNum = followAgentMapper.getOpenAccountNum(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setOpenAccountNum(openNum);

            BigDecimal inCash = followAgentMapper.getInCash(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setInCash(inCash);

            int inCashNum = followAgentMapper.getInCashNum(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setInCashNum(inCashNum);

            BigDecimal outCash = followAgentMapper.getOutCash(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setOutCash(outCash);

            int outCashNum = followAgentMapper.getOutCashNum(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setOutCashNum(outCashNum);

            followUpLog.setId(followUpLogMapper.getNewId());
            followUpLog.setFollowId(followUp.getId());
            followUpLog.setDeptId(followUp.getDeptId());
            followUpLog.setCreateTime(date);
            followUpLog.setUpdateTime(date);

            List<Organization> orgs = followOrgMapper.findOrgsByfollowId(followUp.getId(), endTime, dayiDataBaseStr);

            BigDecimal manageFund = countService.getOrgManageFund(orgs);
            followUpLog.setManageFund(manageFund);

            stratTime = new DateTime(date).plusDays(-2).millisOfDay().withMaximumValue()
                    .plusMinutes(-30).plusSeconds(2).toString("yyyy-MM-dd HH:mm:ss");

            endTime = new DateTime(date).millisOfDay().withMinimumValue().plusMinutes(-30)
                    .toString("yyyy-MM-dd HH:mm:ss");

            FollowUpLog log = followUpLogMapper.getLog(followUp.getId(), stratTime, endTime);

            if (log == null) {
                followUpLog.setManageGrowthFund(manageFund);
            } else {
                followUpLog.setManageGrowthFund(manageFund.subtract(log.getManageFund()));
            }
            followUpLogMapper.add(followUpLog);
        }
        return BizResult.SUCCESS;

    }


}
