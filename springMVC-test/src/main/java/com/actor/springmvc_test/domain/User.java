package com.actor.springmvc_test.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * description: 描述
 *
 * @author : 李大发
 * date       : 2021/4/6 on 22
 * @version 1.0
 */
public class User implements Serializable {
    public String uname;
    public Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")// HH:mm:ss
    public String time;
    public Date birthday;
    public LocalDateTime localDateTime;

//    public String getUname() { return uname; }

    public void setUname(String uname) {
        this.uname = uname;
    }

//    public Integer getAge() { return age; }

    public void setAge(Integer age) {
        this.age = age;
    }

//    public Date getBirthday() { return birthday; }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
