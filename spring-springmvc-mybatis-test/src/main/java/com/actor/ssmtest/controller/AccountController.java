package com.actor.ssmtest.controller;

import com.actor.ssmtest.domain.Account;
import com.actor.ssmtest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Controller/*("accountController")*/
public class AccountController {

    @Autowired
    private AccountService annoService;

    @Value("aaa")
    private String userName;

    public Account findByName() {
        return annoService.findAccountByName(userName);
    }

    @PostConstruct
    private void initMethod() {
        System.out.printf("%s 开始初始化!\n", getClass());
    }

    @PreDestroy
    private void destroyMethod() {
        System.out.printf("%s 销毁实例!\n", getClass());
    }

    /**
     * 转钱
     * @param from 转出人
     * @param to 转入人
     * @param money 转多少钱
     * @param exception 是否测试转钱过程中发生异常.(sql事务)
     */
    public void transferMoney(String from, String to, float money, boolean exception) {
        annoService.transferMoney(from, to, money, exception);
    }

    //查询所有
    public List<Account> findAllAccount() {
        return annoService.findAllAccount();
    }
}
