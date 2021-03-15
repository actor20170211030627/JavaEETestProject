package com.actor.ssmtest._9PlatformTransactionManager_Anno2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//配置要扫描的包
@ComponentScan(basePackages = {"com.actor.ssmtest._9PlatformTransactionManager_Anno",   //service和dao
        "com.actor.ssmtest._9PlatformTransactionManager_Anno2"})                        //自己包

//JdbcConfig.class 是 SpringConfiguration 的子配置类, 添加了这个@Import 注解后, JdbcConfig 不用再添加 @Configuration 注解
@Import(value = {JdbcConfig9.class, TransactionConfig9.class})

//classpath: resources 类路径
@PropertySource(value = "classpath:application.properties")

//开启spirng对注解事务的支持(xml配置: <tx:annotation-driven )
@EnableTransactionManagement
public class SpringConfiguration9 {

}