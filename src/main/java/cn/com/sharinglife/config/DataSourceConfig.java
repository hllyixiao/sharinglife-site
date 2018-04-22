package cn.com.sharinglife.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author hell
 * @date 2018/4/21
 */
@Configuration
@ConditionalOnProperty(name = "sl.primary.datasource.enable", havingValue = "true")
@MapperScan(basePackages = "cn.com.sharinglife.mapper",
        sqlSessionFactoryRef = "primarySqlSessionFactory" )
public class DataSourceConfig {

    @Value("${sl.primary.datasource.xml.mapper.location:}")
    private String xmlMapperLocation;

    @Value("${sl.primary.datasource.xml.config.location:}")
    private String xmlConfigLocation;

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "sl.primary.datasource.link")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(
            @Qualifier("primaryDataSource") DataSource primaryDataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(primaryDataSource);
        if(!Objects.equals("",xmlMapperLocation)){
            sqlSessionFactoryBean.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources(xmlMapperLocation));
        }
        if(!Objects.equals("",xmlConfigLocation)){
            sqlSessionFactoryBean.setConfigLocation(
                    new PathMatchingResourcePatternResolver().getResource(xmlConfigLocation));
        }
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置声明式事务管理器
     */
    @Bean(name = "primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryDataSource") DataSource primaryDataSource){
        return new DataSourceTransactionManager(primaryDataSource);
    }

    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory primarySqlSessionFactory){
        return new SqlSessionTemplate(primarySqlSessionFactory);
    }

}
