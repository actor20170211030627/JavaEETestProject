package com.actor.mybatis_test.dao;

import com.actor.mybatis_test.domain.Role;
import com.actor.mybatis_test.domain.RoleUsers;
import com.actor.mybatis_test.domain.UserRoles;

import java.util.List;

/**
 * description: 角色
 *
 * date       : 2021/5/5 on 22
 * @version 1.0
 */
public interface IRoleDao {

    List<Role> findAll();

    List<RoleUsers> findAllRoleInfos();

    List<UserRoles> findAllUserInfos();
}
