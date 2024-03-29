package com.dayi.follow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author xiell
 * @date 2018/11/13
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableTransactionManagement
@EnableScheduling
@ImportResource("classpath:/dubbo.xml")
@ServletComponentScan(basePackages = "com.dayi.follow.filter")
public class FollowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FollowApplication.class, args);
    }

}


