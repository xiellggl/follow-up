package com.dayi.follow;

import com.dayi.mybatis.support.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author xiell
 * @date 2018/11/13
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableTransactionManagement
public class FollowApplication {

    public static void main(String[] args)  {
        SpringApplication.run(FollowApplication.class, args);
    }

}


