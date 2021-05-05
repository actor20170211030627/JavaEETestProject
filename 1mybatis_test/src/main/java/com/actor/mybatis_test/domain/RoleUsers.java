package com.actor.mybatis_test.domain;

import java.util.List;

/**
 * description: 描述
 *
 * date       : 2021/5/5 on 22
 * @version 1.0
 */
public class RoleUsers extends Role {

    //一个角色可包含多个用户
    public List<User> users;
}
