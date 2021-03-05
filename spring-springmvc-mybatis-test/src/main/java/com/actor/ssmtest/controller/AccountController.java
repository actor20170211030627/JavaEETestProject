package com.actor.ssmtest.controller;

import com.actor.ssmtest.domain.Account;
import com.actor.ssmtest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
}
