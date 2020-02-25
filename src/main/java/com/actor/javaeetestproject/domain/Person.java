package com.actor.javaeetestproject.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * description: 类的描述
 * company    : 重庆了赢科技有限公司 http://www.liaoin.com
 * author     : 李大发
 * date       : 2020/2/25 on 11:13
 */
@Data   //get/set/tostring/...
@TableName("person")
public class Person implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    public Long id;

    @TableField(value = "name", exist = true)
    public String name;

    @TableField(value = "age", exist = true)
    public int age;

    @TableField(value = "sex", exist = true)
    public int sex;

    @TableField(value = "birthday", exist = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date birthday;
}
