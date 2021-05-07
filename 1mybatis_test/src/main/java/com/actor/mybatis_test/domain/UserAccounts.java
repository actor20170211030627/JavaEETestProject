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
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }

//    public String toString2() {
//        return super.toString();
//    }

    public String toString2() {
        return "UserAccounts{" +
                "accounts=" + accounts +
                ", userId=" + userId +
                ", userAge=" + userAge +
                ", userName='" + userName + '\'' +
                ", userBirthday=" + userBirthday +
                ", userSex='" + userSex + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
