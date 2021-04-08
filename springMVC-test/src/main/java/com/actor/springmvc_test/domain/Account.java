package com.actor.springmvc_test.domain;

import com.actor.springmvc_test.utils.JacksonUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public String time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date birthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")//:ss
    public LocalDateTime localDateTime;
    //对象
    public User user;
    //List
    public List<User> list;
    //Map
    public Map<String, User> map;

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

//    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }

//    public Date getBirthday() { return birthday; }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

//    public LocalDateTime getLocalDateTime() { return localDateTime; }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * User 必须有get 方法
     */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * List 必须有get 方法
     */
    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    /**
     * Map 必须有get 方法
     */
    public Map<String, User> getMap() {
        return map;
    }

    public void setMap(Map<String, User> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
