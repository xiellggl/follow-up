package com.dayi.follow.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/inc/**").addResourceLocations("/inc/");
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//        registry.addResourceHandler("/views/**").addResourceLocations("/WEB-INF/views/");
//        super.addResourceHandlers(registry);
//    }


}
