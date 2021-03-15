package com.actor.ssmtest._1Test_IOC_DI;

import com.actor.ssmtest.utils.JacksonUtils;

import java.util.Date;

/**
 * 构造函数注入 测试
 */
public class Customer1 {

    private Long id;
    private String name;
    private Integer age;
    private Date birthday;

    //构造函数注入
    public Customer1(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
