package com.actor.ssmtest.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

//JdbcConfig.class 是 SpringConfiguration 的子配置类, SpringConfiguration 添加了@Import 注解后, JdbcConfig 不用再添加 @Configuration 注解
//@Configuration
public class JdbcConfig {

    /**
     * @param dataSource spring会去容器中查找有没有可用的bean对象. 查找的方式和 @Autowired 注解的作用一样.
     */
    @Bean(name="queryRunner")
    @Scope("prototype")         //不能是单例
    public QueryRunner createQueryRunner(@Qualifier(value = "dataSource2") DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    //在代码里写固定了不好, 改进见下方代码.
//    @Bean("dataSource")
//    public DataSource createDataSource1() {
//        try {
//            ComboPooledDataSource ds = new ComboPooledDataSource();
//            ds.setDriverClass("com.mysql.jdbc.Driver");
//            ds.setJdbcUrl("jdbc:mysql://localhost:3306/spring_boot_test?characterEncoding=utf-8&serverTimezone=GMT%2B8");//不能写 &amp;
//            ds.setUser("root");
//            ds.setPassword("123456");
//            return ds;
//        } catch (PropertyVetoException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    @Bean("dataSource2")
    public DataSource createDataSource2() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(user);
            ds.setPassword(password);
            return ds;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
