package com.actor.ssm_integrate.controller;

import com.actor.ssm_integrate.domain.Account;
import com.actor.ssm_integrate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * description: SSM整合
 *
 * date       : 2021/4/20 on 22
 * @version 1.0
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=204
     * ssm整合说明
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=205
     * ssm整合之搭建环境
     * 1. 导入依赖
     * 2. 创建数据库: {@link com.actor.ssm_integrate.domain.Account}
     *    还有controller, service, dao等
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=206
     * ssm整合之编写Spring框架
     * 在resources右击 -> New -> Xml Configuration File -> Spring Config -> applicationContext
     * 写测试类, log4j测试(可要配置文件)
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=207
     * ssm整合之编写SpringMVC框架
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=208
     * ssm整合之Spring整合SpringMVC的框架
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=209
     * ssm整合之编写MyBatis框架
     * 1.写 {@link com.actor.ssm_integrate.dao.AccountDao}
     * 2.写 resources/SqlMapConfig.xml(仅用于测试, 后面p=211视频会在applicationContext.xml中整合Mybatis)
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=210
     * 测试保存Account
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=211
     * ssm整合之Spring整合Mybatis框架
     * 1.在 applicationContext.xml 中配置整合Mybatis
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=212
     * <!-- 配置Spring框架声明式事务管理 -->
     * <!--1.配置事务管理器-->
     * <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
     *     <property name="dataSource" ref="dataSource"/>
     * </bean>
     * <!--2.配置事务通知-->
     * <tx:advice id="txAdvice" transaction-manager="transactionManager">
     *     <tx:attributes>
     *         <!--find开头, 只读的事务-->
     *         <tx:method name="find*" read-only="true"/>
     *         <!--隔离级别-->
     *         <tx:method name="*" isolation="DEFAULT"/>
     *         <!-- ... -->
     *     </tx:attributes>
     * </tx:advice>
     * <!--3.配置AOP增强-->
     * <aop:config>
     *     <aop:advisor advice-ref="txAdvice" pointcut="execution(public * com.actor.ssm_integrate.service.impl.*ServiceImpl.*(..))" />
     * </aop:config>
     */
    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<Account> all = accountService.findAll();
        model.addAttribute("msg0", all);
        return "success";
    }

    @RequestMapping("/saveAccount")
    public String saveAccount(Account account, Model model) {
        accountService.saveAccount(account);
        model.addAttribute("account", account);
        return "success";
    }
    //测试事务, 制造报错
    @RequestMapping("/testSaveError")
    public String testSaveError(Model model) {
        Account account = new Account();
        account.setName("测试事务, 制造报错");
        account.setMoney(123.4567D);
        accountService.saveAccount(account);
        int i = 1 / 0;
        model.addAttribute("account", account);
        return "success";
    }
}
