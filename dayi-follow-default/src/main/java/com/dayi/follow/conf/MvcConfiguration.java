package com.dayi.follow.conf;

import com.dayi.follow.interceptor.GlobalInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login/**");
        super.addInterceptors(registry);
    }

    //    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//        registry.addResourceHandler("/inc/**").addResourceLocations("/inc/");
//        registry.addResourceHandler("/views/**").addResourceLocations("/WEB-INF/views/");
//    }
}
