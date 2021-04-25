package com.actor.ssm_integrate.controller;

import com.actor.ssm_integrate.domain.Account;
import com.actor.ssm_integrate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * description: SSM整合
 *
 * date       : 2021/4/20 on 22
 * @version 1.0
 */
@Controller
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
     */

    public List<Account> findAll() {
        return accountService.findAll();
    }

    public void saveAccount(Account account) {
        accountService.saveAccount(account);
    }
}
