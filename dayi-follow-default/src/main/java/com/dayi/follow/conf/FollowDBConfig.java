package com.dayi.follow.conf;

import com.dayi.mybatis.spring.spring.SqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
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

    @Autowired
    @Qualifier("followDataSource")
    DataSource followDataSource;

    @Primary
    @Bean(name = "followDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.follow")
    public DataSource followDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public SqlSessionFactory followSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(followDataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resolver.getResources("classpath:com/dayi/follow/dao/follow/mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "followSessionTemplate")
    @Primary
    public SqlSessionTemplate followSqlSession() throws Exception {
        SqlSessionTemplate followSessionTemplate = new SqlSessionTemplate(followSqlSessionFactory());

        return followSessionTemplate;
    }


}
