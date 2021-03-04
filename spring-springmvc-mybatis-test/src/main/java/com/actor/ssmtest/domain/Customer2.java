package com.actor.ssmtest.domain;

import com.actor.ssmtest.utils.JacksonUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * set方法注入 测试
 */
public class Customer2 {

    private Long id;
    private String name;
    private Integer age;
    private Date birthday;

    //复杂类型注入
    private String[] array;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;
    private Properties property;

    //set方法注入
    public void setUserName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperty(Properties property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
