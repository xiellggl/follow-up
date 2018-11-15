package com.dayi.follow.conf;

import com.dayi.follow.service.impl.FollowUpServiceImpl;
import com.dayi.user.authorization.AuthorizationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Configuration
public class AuthConfiguration {


    @Bean
    public AuthorizationManager authorizationManager(FollowUpServiceImpl followUpServiceImpl, FollowUpAuthenticationHandler followUpAuthenticationHandler) throws IOException {

        AuthorizationManager authorizationManager = new AuthorizationManager();
        authorizationManager.setRealm(followUpServiceImpl,followUpAuthenticationHandler);

        return authorizationManager;
    }

}
