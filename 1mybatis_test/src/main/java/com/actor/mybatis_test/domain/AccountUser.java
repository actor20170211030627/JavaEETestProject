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

    //如果懒加载的时候, 调用get方法才会触发查询user
    public User getUser() {
        return user;
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

    //如果懒加载的时候, 调用这个方法, 即使不打印user, 也会触发懒加载
//    @Override
//    public String toString() {
//        return "AccountUser{" +
//                "id=" + id +
//                ", uid=" + uid +
//                ", money=" + money +
//                ", user=" + user +
//                '}';
//    }
}
