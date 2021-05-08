package com.actor.mybatis_test.domain;

import com.actor.mybatis_test.utils.JacksonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * description: 用户表
 * date       : 2021/5/4 on 08
 * @version 1.0
 */
public class User_Anno implements Serializable {

    public Integer id;
    public Integer age;
    public String username;
    public Date birthday;
    public String sex;
    public String address;

    //修改字段名, 和数据库字段不一致!
//    public Integer userId;
//    public Integer userAge;
//    public String userName;
//    public Date userBirthday;
//    public String userSex;
//    public String userAddress;

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
