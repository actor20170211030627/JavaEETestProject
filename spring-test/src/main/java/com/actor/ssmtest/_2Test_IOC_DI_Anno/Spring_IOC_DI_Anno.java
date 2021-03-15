package com.actor.ssmtest._2Test_IOC_DI_Anno;

import com.actor.ssmtest._2Test_IOC_DI_Anno.controller.AccountController;
import com.actor.ssmtest.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description: 注解方式注入
 *  https://www.bilibili.com/video/BV1mE411X7yp?p=104
 *
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */
public class Spring_IOC_DI_Anno {

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=105
     * 1.在配置文件xml中配置要扫描的包 <context:component-scan
     * 2.添加注解 {@link org.springframework.stereotype.Component}
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=106
     * 以下3个注解的作用和属性, 与 @Component 是一模一样的!!!
     * @see org.springframework.stereotype.Controller   一般用于 表现层
     * @see org.springframework.stereotype.Service      一般用于 业务层
     * @see org.springframework.stereotype.Repository   一般用于 持久层
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=107
     * @see org.springframework.beans.factory.annotation.Autowired
     *      自动按照类型注入. 只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配, 就可以注入成功.
     *      出现位置: 变量/方法
     *
     *      //如果 IAccountDao 有多个Impl实现类, 默认找 id=accountDao1 的bean, 否则报错
     *      private IAccountDao accountDao1;
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=108
     * @see org.springframework.beans.factory.annotation.Qualifier
     *      在按照类中注入的基础上再按照名称注入. 它在给类成员注入时不能单独使用. 但是在给方法参数注入时可以单独使用.
     *
     *      @Autowired
     *      @Qualifier("accountDao1")        //查找 id=accountDao1 的Dao(当有多个Dao的时候)
     *      private IAccountDao accountDao;
     *
     *      //在方法上指定DataSource, 参数里可以单独使用.
     *      public QueryRunner createQueryRunner(@Qualifier(value = "dataSource2") DataSource dataSource) {
     *      }
     *
     * @see javax.annotation.Resource(name = "")
     *      可以自动注入, name属性指定bean的id, 例:
     *      @Resource(name = "accountDao1")
     *      private IAccountDao accountDao;
     *
     * @see org.springframework.beans.factory.annotation.Value(value = "")
     *      @Value: 用于注入基本类型和String类型的数据
     *      value: 用于指定数据的值. 可以使用spring中 SpEL(也就是spring的el表达式)
     *          //SpEL的写法: ${表达式}
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=109
     * @see org.springframework.context.annotation.Scope(value = "")
     *      @Scope: 作用域范围, 写在类/方法上(作用于方法返回值)
     *      value取值:
     *          1.singleton     : 单例(默认值), spring容器中只包含1份bean的实例
     *          2.prototype     : 多例
     *          3.request       : 作用于web引用的请求范围
     *          4.session       : 作用于web应用的会话范围
     *          5.global-session: 作用于web集群环境的会话范围(全局会话范围), 当不是集群环境时, 它就是session
     *
     * @see javax.annotation.PostConstruct  用于指定初始化方法, init-method
     * @see javax.annotation.PreDestroy     用于指定销毁方法, destroy-method
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=110
     * https://www.bilibili.com/video/BV1mE411X7yp?p=111
     * https://www.bilibili.com/video/BV1mE411X7yp?p=112
     * https://www.bilibili.com/video/BV1mE411X7yp?p=113
     */
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("_2Spring_IOC_DI_Anno.xml");

        Customer3Anno customer3Anno = ac.getBean("customer3Anno", Customer3Anno.class);
        System.out.printf("@Component 注解方式注入 获取的对象: %s\n", customer3Anno);

        //注解注入示例
        AccountController accountController = ac.getBean("accountController", AccountController.class);
        System.out.printf("@Controller 注解方式注入 获取的对象: %s\n", accountController);
        Account account = accountController.findByName();
        System.out.println(account);
    }
}
