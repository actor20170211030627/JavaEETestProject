package com.actor.mybatis_test.domain;

import com.actor.mybatis_test.utils.JacksonUtils;

import java.io.Serializable;

/**
 * description: 账户表
 * date       : 2021/4/25 on 23
 * @version 1.0
 */
public class AccountUser extends Account {

    //账户绑定一个User, 多对一
    public User user;

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }


    public String toString2() {
        return "AccountUser{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
