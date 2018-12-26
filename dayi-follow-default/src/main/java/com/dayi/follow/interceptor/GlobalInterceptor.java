package com.dayi.follow.interceptor;

import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.service.RoleService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(GlobalInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
