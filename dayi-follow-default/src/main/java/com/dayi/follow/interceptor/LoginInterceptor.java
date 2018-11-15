package com.dayi.follow.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
/**
 * @author xiell
 * @date 2018/11/13
 */

public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }

    /**
     * 处理返回信息
     *
     * @param request
     * @param response
     */
    private void handleForward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws Exception {
        response.sendRedirect("/forward/finance/identity_forward.jsp?errmsg=" + URLEncoder.encode(errorMessage, "UTF-8"));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {



    }
}
