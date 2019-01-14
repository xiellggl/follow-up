package com.dayi.follow.conf;

import com.dayi.mybatis.spring.plus.MybatisSqlSessionFactoryBean;
import com.dayi.mybatis.spring.plus.plugins.page.PaginationInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Configuration
@MapperScan(basePackages = {"com.dayi.follow.dao.follow"},sqlSessionFactoryRef = "followSqlSessionFactory")
public class FollowDBConfig {

    @Bean(name = "FollowHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.follow")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "followDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.follow")
    public DataSource followDataSource(){
        return new HikariDataSource(hikariConfig());
    }

    @Bean(name = "followSqlSessionFactory")
    @Primary
    public SqlSessionFactory followSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(followDataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resolver.getResources("classpath:com/dayi/follow/dao/follow/mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new PaginationInterceptor()});
        sqlSessionFactoryBean.afterPropertiesSet();

        return sqlSessionFactoryBean.getObject();
    }
}
