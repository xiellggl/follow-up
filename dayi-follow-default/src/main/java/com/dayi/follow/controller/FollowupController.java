package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.web.util.IPUtil;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.enums.MemberStatusEnum;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.vo.LoginVo;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import com.dayi.user.authorization.authc.support.UsernamePasswordToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/followup")
public class FollowupController {
    @Resource
    FollowUpService followUpService;
    @Resource
    UserComponent userComponent;

    /**
     * 默认跳转登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"","/"})
    public String index(HttpServletRequest request){
        LoginVo currVo = userComponent.getCurrUser(request);
        if(null == currVo){
            return "redirect:/followup/login";
        }else if (currVo.getUsername().equals("admin") || currVo.getIsAdmin().equals(1)) { // 当 超级管理员 或者 管理员登录时，默认进入跟进人分配页面
            return "redirect:/followup/manage/index";
        } else {
            return "redirect:/followup/uc/index";
        }
    }

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
        if (result.hasErrors()) {//参数错误，拼接返回
            List<String> errorMsgList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorMsgList.add(error.getDefaultMessage());
            }
            return BizResult.fail((StringUtils.join(errorMsgList, ",")));
        } else {
            //使用用户中心api进行登录
            boolean b = AuthorizationManager.login(request, new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword(), IPUtil.getIp(request)));

            if (b) {//登录成功
                LoginVo currVo = userComponent.getCurrUser(request);
                if (currVo != null) {
                    if (currVo.getDisable() == MemberStatusEnum.ENABLE.getValue()) {
                        String goToUrl;
                        if (StringUtils.isNotBlank(goTo)) {//跳转
                            goToUrl = goTo;
                        } else if (currVo.getUsername().equals("admin") || currVo.getIsAdmin().equals(1)) { // 当 超级管理员 或者 管理员登录时，默认进入跟进人分配页面
                            goToUrl = "/followup/manage/index";
                        } else {
                            goToUrl = "/followup/uc/index";
                        }
                        return BizResult.succ(goToUrl, "登录成功！");
                    } else {
                        return BizResult.fail("登录失败！");
                    }
                } else {
                    return BizResult.fail("登录失败！");
                }
            } else {//登录失败
                return BizResult.fail("账号密码错误！");
            }
        }
    }


//    @RequestMapping("loginout")
//    public String loginOut(HttpServletRequest request, HttpServletResponse response, Model model) {
//        FlowUpLoginVo vo = financeUserComponent.getCurrentLoginFollowUp(request);
//        if (vo != null) {
//            flowUpService.loginOut(vo.getUserName());
//        }
//        financeUserService.createPulblicKey(request);
//        // 退出用户中心
//        AuthorizationManager.cleanAllAuthenticationInfo(request,response);
//        return "redirect:/followup/login";
//    }

    /**
     * 新增 -- 保存 -- 跟进人
     */
    @RequestMapping("/add/save")
    @ResponseBody
    public BizResult add(HttpServletRequest request, @Valid FollowUp followUp) {
        LoginVo currVo = userComponent.getCurrUser(request);
        if (currVo == null || (!"admin".equals(currVo.getUsername()) && !currVo.getIsAdmin().equals(1))) {
            return BizResult.FAIL;
        }
        try {
            followUp.setCreateBy(currVo.getName());
            followUp.setModifyBy(currVo.getName());
            BizResult bizResult = followUpService.add(followUp);
            return bizResult;
        } catch (Exception e) {
            return BizResult.fail(e.getMessage());
        }
    }


}
