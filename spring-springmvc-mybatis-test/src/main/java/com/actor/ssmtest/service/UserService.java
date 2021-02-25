package com.actor.ssmtest.service;

import com.actor.ssmtest.domain.User;

public interface UserService {

    //执行登陆业务
    User login(User user);
}
