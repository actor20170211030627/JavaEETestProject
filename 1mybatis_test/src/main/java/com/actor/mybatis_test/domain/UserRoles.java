package com.actor.mybatis_test.domain;

import java.util.List;

/**
 * description: 描述
 *
 * date       : 2021/5/5 on 22
 * @version 1.0
 */
public class UserRoles extends User {

    //一个用户可包含多个角色
    public List<Role> roles;
}
