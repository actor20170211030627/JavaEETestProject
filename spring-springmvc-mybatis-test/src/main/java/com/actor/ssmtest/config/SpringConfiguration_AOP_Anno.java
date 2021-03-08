package com.actor.ssmtest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

//@Configuration
@ComponentScan(basePackages = {"com.actor.ssmtest.domain", "com.actor.ssmtest.utils"})

//代替xml中的配置:
// <beans>
//     <!-- 配置spring开启注解 AOP 的支持, 可以使用@EnableAspectJAutoProxy -->
//     <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
// </beans>
@EnableAspectJAutoProxy
public class SpringConfiguration_AOP_Anno {

}
