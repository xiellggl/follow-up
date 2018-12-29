package com.dayi.follow.conf;

import com.dayi.user.authorization.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author xiell
 * @date 2018/11/13
 */
@Configuration
public class AuthFilterConfiguration {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(authenticationFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/");
        return registrationBean;
    }

    @Bean
    public AuthenticationFilter authenticationFilter(){
        return new AuthenticationFilter();
    }


}
