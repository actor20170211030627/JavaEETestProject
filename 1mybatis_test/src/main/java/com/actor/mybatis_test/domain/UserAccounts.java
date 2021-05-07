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

    //如果懒加载的时候, 调用get方法才会触发查询accounts
    public List<Account> getAccounts() {
        return accounts;
    }

    //如果懒加载的时候, 调用这个方法会报错
    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }

    //如果懒加载的时候, 调用这个方法会报错
//    @Override
//    public String toString() {
//        return super.toString();
//    }

    //如果懒加载的时候, 调用这个方法, 即使不打印accounts, 也会触发懒加载
//    @Override
//    public String toString() {
//        return "UserAccounts{" +
//                "accounts=" + accounts +
//                ", userId=" + userId +
//                ", userAge=" + userAge +
//                ", userName='" + userName + '\'' +
//                ", userBirthday=" + userBirthday +
//                ", userSex='" + userSex + '\'' +
//                ", userAddress='" + userAddress + '\'' +
//                '}';
//    }
}
