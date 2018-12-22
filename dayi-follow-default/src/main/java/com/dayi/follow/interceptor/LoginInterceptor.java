package com.dayi.follow.interceptor;

import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.UserService;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Resource
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        String requestURI = request.getRequestURI();
        request.getSession().setAttribute("requestURI",requestURI);

        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        String userId = accountInfo.getUserId();

        FollowUp user = userService.get(userId);
        request.getSession().setAttribute("name",user.getName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
