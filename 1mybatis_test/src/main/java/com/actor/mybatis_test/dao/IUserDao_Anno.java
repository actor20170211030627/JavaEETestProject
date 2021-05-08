package com.actor.mybatis_test.dao;

import com.actor.mybatis_test.domain.User;
import com.actor.mybatis_test.domain.UserAccounts;
import com.actor.mybatis_test.domain.User_Anno;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * description: 用户持久层接口
 * <p>
 * date       : 2021/5/4 on 08
 *
 * @version 1.0
 */
@CacheNamespace(blocking = true)
public interface IUserDao_Anno {

    @Select("select * from user")
    List<User_Anno> findAll();

    @Select("select * from user where id = #{id}")
    User_Anno findById(Integer id);

    @Insert("insert into user(username, birthday, sex, address) VALUES (#{user.username}, #{user.birthday}, #{user.sex}, #{user.address})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "user.id", before = false, resultType = int.class)
    void saveUser(@Param("user") User_Anno user);

    //更新User
    @Update("update user set username = #{username}, birthday = #{birthday}, sex = #{sex}, address = #{address} where id = #{id}")
    void updateUser(User_Anno user);

    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    //模糊查询, 这儿需要提供百分号, 例: "%王%"
    @Select("select * from user where username like #{userName}")
    List<User_Anno> findByName1(String userName);

    /**
     * 模糊查询, 这儿不需要提供百分号, 例: 王
     * 了解, 一般不这样开发, 防止sql注入
     * 打印的sql语句: select * from user where username like '%王%'
     */
    @Deprecated
    @Select("select * from user where username like '%${userName}%'")
    List<User_Anno> findByName2(@Param("userName") String userName, /*@Param("p2") */int testParam2);

    //查询总用户数
    @Select("select count(1) from user")
    int findTotal();


    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, property = "userId", column = "id"),
            @Result(property = "userAge", column = "age"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userBirthday", column = "birthday"),
            @Result(property = "userSex", column = "sex"),
            @Result(property = "userAddress", column = "address")
    })
    List<User> findAllUser();


    @Select("select * from user")
    @Results(id = "userAccountsMap", value = {
            @Result(id = true, property = "userId", column = "id"),
            @Result(property = "userAge", column = "age"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userBirthday", column = "birthday"),
            @Result(property = "userSex", column = "sex"),
            @Result(property = "userAddress", column = "address"),
            @Result(property = "accounts",
                    column = "id",//user的id
                    /**
                     * @see IAccountDao#findAccountsByUserId(int)
                    */
                    many = @Many(select = "com.actor.mybatis_test.dao.IAccountDao.findAccountsByUserId",
                            //懒加载
                            fetchType = FetchType.LAZY))
    })
    List<UserAccounts> findAllUserAccounts();
}
