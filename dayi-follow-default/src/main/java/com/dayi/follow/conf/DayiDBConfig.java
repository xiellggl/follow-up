package com.dayi.follow.conf;

import com.dayi.mybatis.spring.plus.MybatisSqlSessionFactoryBean;
import com.dayi.mybatis.spring.plus.plugins.page.PaginationInterceptor;
import com.dayi.mybatis.spring.spring.SqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
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
@MapperScan(basePackages = {"com.dayi.follow.dao.dayi"},sqlSessionFactoryRef = "dayiSqlSessionFactory")
public class DayiDBConfig {

    @Autowired
    @Qualifier("dayiDataSource")
    DataSource dayiDataSource;

    @Bean(name = "dayiDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dayi")
    public DataSource dayiDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "dayiSqlSessionFactory")
    public SqlSessionFactory dayiSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dayiDataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resolver.getResources("classpath:com/dayi/follow/dao/dayi/mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new PaginationInterceptor()});
        sqlSessionFactoryBean.afterPropertiesSet();

        return sqlSessionFactoryBean.getObject();
    }


}
