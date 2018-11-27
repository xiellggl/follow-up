package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.common.web.util.IPUtil;
import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.enums.MemberStatusEnum;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.util.Md5Util;
import com.dayi.follow.vo.LoginVo;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.support.UsernamePasswordToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Date;
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

    private static Logger logger = LoggerFactory.getLogger(FollowupController.class);

    /**
     * 登录表单页面
     *
     * @param request
     * @param goTo
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, String goTo) {
        for(int i=1;i<2000;i++){

        }
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
                        } else {
                            goToUrl = "/followup/index";
                        }
                        return BizResult.succ(goToUrl, "登录成功！");
                    } else {
                        return BizResult.fail("账号已被禁用！");
                    }
                } else {
                    return BizResult.fail("登录失败！");
                }
            } else {//登录失败
                return BizResult.fail("账号密码错误！");
            }
        }
    }


    @RequestMapping("loginout")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
        AuthorizationManager.cleanAllAuthenticationInfo(request, response);
        return "redirect:/followup/login";
    }

    /**
     * 新增 -- 保存 -- 跟进人
     */
    @RequestMapping("/add/save")
    @ResponseBody
    public BizResult add(HttpServletRequest request, @Valid FollowUp followUp, BindingResult result) {
        BizResult bizResult = checkErrors(result);

        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        LoginVo currVo = userComponent.getCurrUser(request);

        if (followUpService.getByUserName(followUp.getUserName()) != null) {
            return BizResult.fail("用户名已存在！");
        }

        if (!followUpService.checkCodeRepeat(followUp.getInviteCode())) {
            return BizResult.fail("邀请码已存在！");
        }

        followUp.setPassword(Md5Util.md5(followUp.getUserName(), followUp.getPassword()));
        followUp.setCreateBy(currVo.getName());
        followUp.setModifyBy(currVo.getName());
        return followUpService.addFollowUp(followUp);


    }


    /**
     * 修改 -- 保存 -- 跟进人
     */
    @RequestMapping("/update/save")
    @ResponseBody
    public BizResult updateSave(HttpServletRequest request, @Valid FollowUp followUp, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        LoginVo currVo = userComponent.getCurrUser(request);
        followUp.setModifyBy(currVo.getName());
        followUp.setUpdateTime(new Date());
        return followUpService.update(followUp);


    }


}
