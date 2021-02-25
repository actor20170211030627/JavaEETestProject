package com.actor.spring_boot_test.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * description: 类的描述
 * author     : 李大发
 * date       : 2020/2/25 on 11:13
 */
//@ApiModel
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"/*, timezone = "GMT+8"*/)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty(value = "生日(yyyy-MM-dd HH:mm:ss)")
    public Date birthday;
}
