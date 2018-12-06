package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.util.DateUtil;
import com.dayi.common.web.util.IPUtil;
import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.conf.Constants;
import com.dayi.follow.enums.MemberStatusEnum;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.UserService;
import com.dayi.follow.util.Md5Util;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.vo.user.FollowUpEditDto;
import com.dayi.follow.vo.user.UserVo;
import com.dayi.follow.vo.LoginVo;
import com.dayi.mybatis.support.Page;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.support.UsernamePasswordToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
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
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 登录表单页面
     *
     * @param request
     * @param goTo
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, String goTo) {
        request.setAttribute("goTo", goTo);
        return "login";
    }


    /**
     * 登录
     */
    @RequestMapping("/login/post")
    @ResponseBody
    public BizResult login(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("loginVo") LoginVo loginVo, String goTo, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) {
            return bizResult;//参数传入错误
        } else {
            boolean b = AuthorizationManager.login(request, new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword(), IPUtil.getIp(request)));
            if (!b) return BizResult.fail("账号密码错误！");
            LoginVo currVo = userComponent.getCurrUser(request);
            if (currVo == null) return BizResult.fail("登录失败！");
            if (currVo.getDisable() != MemberStatusEnum.ENABLE.getValue()) return BizResult.fail("账号已被禁用！");
            String goToUrl;
            if (StringUtils.isNotBlank(goTo)) {//跳转
                goToUrl = goTo;
            } else {
                goToUrl = "/index";
            }
            return BizResult.succ(goToUrl, "登录成功！");
        }
    }


    @RequestMapping("loginout")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
        AuthorizationManager.cleanAllAuthenticationInfo(request, response);
        return "redirect:/user/login";
    }

    @RequestMapping(value = "/list")
    public String list(HttpServletRequest request, Model model, Page page) {
        LoginVo currVo = userComponent.getCurrUser(request);

        String mobile = request.getParameter("mobile");
        String queryDeptId = request.getParameter("deptId");
        String inviteCode = request.getParameter("inviteCode");

        page.setPageSize(Constants.DEFAULT_PAGE_SIZE);

        page = userService.findPage(page, currVo.getDeptId(), mobile, queryDeptId, inviteCode);

        //List<Department> deptList = deptService.getSubDept(currVo.getDeptId());

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求
        model.addAttribute("pageUrl", pageUrl);
        model.addAttribute("page", page);
        return "sys/user_list";
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
     * 修改 -- 保存 -- 用户
     */
    @RequestMapping("/update/save")
    @ResponseBody
    public BizResult updateSave(HttpServletRequest request, @Valid FollowUpEditDto followUpEditDto, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        LoginVo currVo = userComponent.getCurrUser(request);
        followUpEditDto.setUpdateBy(currVo.getName());
        followUpEditDto.setUpdateTime(new Date());
        return userService.update(followUpEditDto);


    }

    /**
     * 修改 -- 保存 -- 用户
     */
    @RequestMapping("/update")
    public String updateSave(HttpServletRequest request, @PathVariable String id, Model model) {
        FollowUp followUp = userService.get(id);
        List<Department> topDeptList = deptService.getTopList();
        List<Department> departments = deptService.doDeptTreeName(topDeptList, 0);
        model.addAttribute("deptList", departments);  // 上级部门下拉选择数据
        model.addAttribute("followUp", followUp);
        return "";


    }

    /**
     * 修改 -- 保存 -- 用户
     */
    @RequestMapping("/add")
    public String updateSave(HttpServletRequest request, Model model) {
        List<Department> topDeptList = deptService.getTopList();
        List<Department> departments = deptService.doDeptTreeName(topDeptList, 0);
        model.addAttribute("deptList", departments);  // 上级部门下拉选择数据
        return "";


    }


    /**
     * 禁用 -- 用户
     */
    @RequestMapping(value = "/disable/{id}")
    @ResponseBody
    public BizResult disable(HttpServletRequest request, @PathVariable String id) {
        LoginVo currVo = userComponent.getCurrUser(request);

        FollowUp followUp = userService.get(id);
        if (followUp == null) return BizResult.fail("用户不存在.");

        followUp.setUpdateBy(currVo.getName());
        return userService.disable(followUp);
    }

    /**
     * 启用 -- 用户
     */
    @RequestMapping(value = "/enable/{id}")
    @ResponseBody
    public BizResult enable(HttpServletRequest request, @PathVariable String id) {
        LoginVo currVo = userComponent.getCurrUser(request);

        FollowUp followUp = userService.get(id);
        if (followUp == null) return BizResult.fail("用户不存在.");

        followUp.setUpdateBy(currVo.getName());
        return userService.enable(followUp);
    }

    /**
     * 重置密码 -- 用户
     */
    @RequestMapping(value = "/resetpwd/{id}")
    @ResponseBody
    public BizResult manageResetPwd(@PathVariable String id, @RequestParam("pwd") String pwd, HttpServletRequest request) {
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
    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public BizResult delete(HttpServletRequest request, @PathVariable String id) {
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

        FollowUp followUp = userService.get(currVo.getId());

        Department department = deptService.get(currVo.getDeptId());

        UserVo userVo = new UserVo();
        userVo.setName(followUp.getName());
        userVo.setUserName(followUp.getUserName());

        if (department != null) {
            userVo.setDepartment(department);
            userVo.setDeptName(department.getName());
        }

        userVo.setInviteCode(followUp.getInviteCode());
        userVo.setCreateTimeFm(DateUtil.formatDate(followUp.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        userVo.setUpdateTimeFm(DateUtil.formatDate(followUp.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));

        model.addAttribute("user", userVo);
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
        return userService.editPwd(currVo.getId(), oldPwd, newPwd, confirmNewPwd);
    }


}
