package com.actor.ssmtest._9PlatformTransactionManager_Anno2;

import com.actor.ssmtest._3Test_IOC_DI_Anno.JdbcConfig3;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//@Configuration
public class JdbcConfig9 {

    @Bean
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource createDataSource2() {
        try {
            /**
             * @see JdbcConfig3#createDataSource2()
             */
//            ComboPooledDataSource ds = new ComboPooledDataSource();//com.mchange.v2.c3p0.ComboPooledDataSource
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName(driver);
            ds.setUrl(url);
            ds.setUsername(user);
            ds.setPassword(password);
            return ds;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
