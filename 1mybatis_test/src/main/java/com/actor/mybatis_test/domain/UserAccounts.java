package com.actor.mybatis_test.domain;

import com.actor.mybatis_test.utils.JacksonUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * description: 用户表
 * date       : 2021/5/4 on 08
 * @version 1.0
 */
public class UserAccounts extends User {

    //一个用户多个账户
    public List<Account> accounts;

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
