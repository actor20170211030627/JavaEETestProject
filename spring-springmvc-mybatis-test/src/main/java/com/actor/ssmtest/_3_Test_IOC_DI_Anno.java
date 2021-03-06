package com.actor.ssmtest;

import com.actor.ssmtest.controller.AccountController;
import com.actor.ssmtest.domain.Account;
import com.actor.ssmtest.config.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * description: 注解方式注入
 *  https://www.bilibili.com/video/BV1mE411X7yp?p=114
 *
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */
public class _3_Test_IOC_DI_Anno {

    public static void main(String[] args) {
        /**
         * https://www.bilibili.com/video/BV1mE411X7yp?p=114
         * @see Configuration
         *      作用: 指定当前类是一个配置类(xxx.xml)
         *      细节: 当该配置类作为 AnnotationConfigApplicationContext 对象创建的参数时, 该注解可以不写
         *
         * @see ComponentScan(basePackages/value = {"com.xxx", "config"})
         *      指定创建容器时要扫描的包.<context:component-scan base-package="com.xxx"/>
         *
         *
         * https://www.bilibili.com/video/BV1mE411X7yp?p=115
         * @see org.springframework.context.annotation.Bean(name/value = {""})
         *      作用: 把当前方法返回值作为ben对象存入spring的ioc容器中
         *      属性:
         *          name/value: 指定bean的id. 可不写, 默认值是当前'方法的名称'
         *      细节:
         *          当我们使用这个注解配置时, 如果方法有参数, spring会去容器中查找有没有可用的bean对象.
         *          查找的方式和 @Autowired 注解的作用一样.
         *
         *
         * https://www.bilibili.com/video/BV1mE411X7yp?p=116
         * https://www.bilibili.com/video/BV1mE411X7yp?p=117
         * @see org.springframework.context.annotation.Import(value = {""})
         *      作用: 导入其它的配置类
         *            有Import 注解的类就是父配置类, 而导入的都在子配置类.
         *      属性:
         *          value: 用于指定其它配置类的字节码.
         *
         *
         * https://www.bilibili.com/video/BV1mE411X7yp?p=118
         * @see org.springframework.context.annotation.PropertySource(value = {""})
         *      作用:
         *          用于指定properties文件位置
         *      属性:
         *          value: 指定文件的名称和路径.
         *          关键字: classpath, 表示类路径下
         *              会去编译后的target/classes目录下去找配置的文件, 加上"classpath:"前缀, 能识别类路径.
         *
         *
         * https://www.bilibili.com/video/BV1mE411X7yp?p=119
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class/*, JdbcConfig.class*/);

        //注解注入示例
        AccountController accountController = ac.getBean("accountController", AccountController.class);
        System.out.printf("@Controller 注解方式注入 获取的对象: %s\n", accountController);
        Account account = accountController.findByName();
        System.out.println(account);
    }
}
