package com.actor.springmvc_test.domain;

import java.io.Serializable;

/**
 * description: 描述
 *
 * @author : 李大发
 * date       : 2021/4/6 on 22
 * @version 1.0
 */
public class Account implements Serializable {
    public String username;
    public String password;
    public double money;
    public User user;

//    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

//    public double getMoney() { return money; }

    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * User 必须有get 方法
     * @return
     */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", user=" + user +
                '}';
    }
}
