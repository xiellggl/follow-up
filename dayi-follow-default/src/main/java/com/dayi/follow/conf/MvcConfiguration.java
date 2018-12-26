package com.dayi.follow.conf;

import com.dayi.follow.filter.GlobalFilter;
import com.dayi.follow.interceptor.GlobalInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Resource
    GlobalInterceptor loginInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("frame");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        String[] excludes = new String[]{"/user/login/**", "/user/loginout"};
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludes);
//        super.addInterceptors(registry);
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/inc/**").addResourceLocations("/inc/");
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//        registry.addResourceHandler("/views/**").addResourceLocations("/WEB-INF/views/");
//        super.addResourceHandlers(registry);
//    }


}
