package com.ywd.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

import static com.alibaba.druid.pool.DruidDataSourceFactory.*;

/**
 * Created by admin on 2017/6/30.
 */
@Configuration
@PropertySource("classpath:application.properties")
@MapperScan(basePackages = {"com.ywd.mapper"})
public class MybatisConfig {

    @Autowired
    private Environment evt;

    /**
     * 创建数据源
     */
    @Bean
    public DataSource getDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", evt.getProperty("spring.datasource.driver"));
        props.put("url", evt.getProperty("spring.datasource.url"));
        props.put("username", evt.getProperty("spring.datasource.username"));
        props.put("password", evt.getProperty("spring.datasource.password"));
        return createDataSource(props);
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*.xml"));//指定xml文件位置
        return factory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws Exception {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
