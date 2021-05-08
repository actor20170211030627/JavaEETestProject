package com.actor.mybatis_test.dao;

import com.actor.mybatis_test.domain.AccountUser;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * description: 描述
 *
 * date       : 2021/5/5 on 17
 * @version 1.0
 */
public interface IAccountDao_Anno {

    @Select("select * from account")
    @Results(id = "accountUserMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(property = "user",
                    column = "uid",
                    /**
                     * @see IUserDao#findById(Integer)
                    */
                    one = @One(select = "com.actor.mybatis_test.dao.IUserDao.findById",
                            fetchType = FetchType.DEFAULT))
    })
    List<AccountUser> findAll();
}
