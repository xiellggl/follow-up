package com.dayi.follow.component;

import com.dayi.follow.model.FollowUp;
import com.dayi.follow.service.FollowUpService;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xiell
 * @date 2018/11/14
 */
@Component
public class UserComponent {
    @Resource
    FollowUpService followUpService;
    public  FollowUp getCurrUser(HttpServletRequest request){
        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        String userId = accountInfo.getUserId();
        return followUpService.get(userId);
    }
}