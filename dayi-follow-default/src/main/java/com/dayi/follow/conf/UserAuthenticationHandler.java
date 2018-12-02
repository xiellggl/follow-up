package com.dayi.follow.conf;

import com.dayi.common.util.Misc;
import com.dayi.follow.service.impl.FollowUpServiceImpl;
import com.dayi.follow.service.impl.UserServiceImpl;
import com.dayi.user.authorization.AuthenticationHandler;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Service(value = "userAuthenticationHandler")
public class UserAuthenticationHandler implements AuthenticationHandler {


    protected Logger logger = LoggerFactory.getLogger(UserAuthenticationHandler.class);

    @Resource
    UserServiceImpl userServiceImpl;

    @Override
    public Realm getRealm() {
        return userServiceImpl;
    }

    @Override
    public boolean handlerAuthorized(HttpServletRequest request, HttpServletResponse response, AuthorizationManager.AuthorizedResult authorizedResult) {
        if(isAjax(request) && !Misc.isEmpty(request.getParameter("power"))){
            try {
                response.setContentType("text/html; charset=utf-8");
                response.getWriter().write("{\"status\" : \"200\", \"succ\" : true, \"msg\" :\"授权认证通过\"}");
                response.getWriter().flush();
            }catch (IOException e){

            }
            return false;
        }
        return true;
    }

    @Override
    public boolean handlerAuthenticated(HttpServletRequest request, HttpServletResponse response, AuthorizationManager.AuthorizedResult authorizedResult) {
    //    String unAuthorizedUrl = followUpServiceImpl.getUnAuthorizedUrl(null);//登陆通过，没有权限
        try {
      //      response.sendRedirect(unAuthorizedUrl);
            String message="未分配权限";
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write("{\"status\" : \"403\", \"msg\" :\""+ message+"\"}");
            response.getWriter().flush();
        } catch (IOException e) {
        }
        return false;
    }

    @Override
    public boolean handlerUnAuthenticated(HttpServletRequest request, HttpServletResponse response, AuthorizationManager.AuthorizedResult authorizedResult) {
      //  String unAuthenticatedUrl = followUpServiceImpl.getUnAuthenticatedUrl(null);
        try {
       //     response.sendRedirect(unAuthenticatedUrl);
            String message="登录超时";
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write("{\"status\" : \"300\", \"succ\" : true, \"msg\" :\""+ message+"\"}");
            response.getWriter().flush();
        } catch (IOException e) {
        }
        return false;
    }

    @Override
    public boolean handlerUnInitialization(HttpServletRequest request, HttpServletResponse response, AuthorizationManager.AuthorizedResult authorizedResult) {
        return false;
    }
    /**
     * 判断异步请求
     * @param request HttpServletRequest
     * @return bool
     */
    private boolean isAjax(HttpServletRequest request){
        String xrw = request.getHeader("X-Requested-With");
        String ajax = request.getParameter("ajax");
        return Misc.eq(xrw,"XMLHttpRequest") || !Misc.isEmpty(ajax);
    }

}

