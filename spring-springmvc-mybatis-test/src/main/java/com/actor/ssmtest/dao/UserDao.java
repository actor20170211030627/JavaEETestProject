package com.actor.ssmtest.dao;

import com.actor.ssmtest.domain.User;

public interface UserDao {

    //根据登陆名返回User
    User getByUserCode(String user_code);
}
