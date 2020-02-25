package com.actor.javaeetestproject.viewmodel;

import com.actor.javaeetestproject.domain.Person;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * description: 类的描述
 * company    : 重庆了赢科技有限公司 http://www.liaoin.com
 * author     : 李大发
 * date       : 2020/2/25 on 16:23
 */
public class PersonDto {

    public String name;

    public int age;

    public int sex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date birthday;

    public Person toPerson() {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setSex(sex);
        person.setBirthday(birthday);
        return person;
    }
}
