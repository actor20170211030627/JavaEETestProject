package com.actor.mybatis_test.dto;

import com.actor.mybatis_test.domain.Account;
import com.actor.mybatis_test.utils.JacksonUtils;

/**
 * description: 描述
 *
 * date       : 2021/5/5 on 17
 * @version 1.0
 */
public class AccountDto extends Account {

    //附带User信息
    public int uid;
    public String username;
    public String address;

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
