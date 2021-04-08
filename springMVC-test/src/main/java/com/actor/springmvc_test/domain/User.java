package com.actor.springmvc_test.domain;

import com.actor.springmvc_test.utils.JacksonUtils;
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

//    public String getUname() { return uname; }

    public void setUname(String uname) {
        this.uname = uname;
    }

//    public Integer getAge() { return age; }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
