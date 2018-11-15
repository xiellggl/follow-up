package com.dayi.follow.conf;

import com.dayi.follow.service.FollowUpService;
import com.dayi.user.authorization.AuthenticationHandler;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.realm.support.SimpleAuthenticationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Service(value = "followUpAuthenticationHandler")
public class FollowUpAuthenticationHandler extends SimpleAuthenticationHandler implements AuthenticationHandler {


    protected Logger logger = LoggerFactory.getLogger(FollowUpAuthenticationHandler.class);

    @Override
    public boolean handlerAuthorized(HttpServletRequest request, HttpServletResponse response, AuthorizationManager.AuthorizedResult authorizedResult) {
        return true;
    }

    @Override
    public boolean handlerAuthenticated(HttpServletRequest request, HttpServletResponse response, AuthorizationManager.AuthorizedResult authorizedResult) {
        return super.handlerAuthenticated(request, response, authorizedResult);
    }

    @Override
    public boolean handlerUnAuthenticated(HttpServletRequest request, HttpServletResponse response, AuthorizationManager.AuthorizedResult authorizedResult) {
        return super.handlerUnAuthenticated(request, response, authorizedResult);
    }
}

