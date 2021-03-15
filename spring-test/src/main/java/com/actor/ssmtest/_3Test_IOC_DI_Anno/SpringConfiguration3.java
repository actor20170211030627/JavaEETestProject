package com.actor.ssmtest._3Test_IOC_DI_Anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

//细节: 当该配置类作为 AnnotationConfigApplicationContext 对象创建的参数时, @Configuration 该注解可以不写
//@Configuration

@ComponentScan(basePackages = {"com.actor.ssmtest._2Test_IOC_DI_Anno",  //controller, service, dao
        "com.actor.ssmtest._3Test_IOC_DI_Anno"})

//JdbcConfig.class 是 SpringConfiguration 的子配置类, 添加了这个@Import 注解后, JdbcConfig 不用再添加 @Configuration 注解
@Import(value = {JdbcConfig3.class})

@PropertySource(value = "classpath:application.properties") //classpath: resources 类路径
public class SpringConfiguration3 {

}
