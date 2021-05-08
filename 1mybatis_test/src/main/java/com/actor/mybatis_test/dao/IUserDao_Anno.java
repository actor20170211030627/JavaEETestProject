package com.actor.mybatis_test.dao;

import com.actor.mybatis_test.domain.User;
import com.actor.mybatis_test.domain.UserAccounts;
import com.actor.mybatis_test.domain.User_Anno;
import com.actor.mybatis_test.vo.QueryVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * description: 用户持久层接口
 * <p>
 * date       : 2021/5/4 on 08
 *
 * @version 1.0
 */
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

    void deleteUser(Integer id);

    //模糊查询, 这儿需要提供百分号, 例: "%王%"
    List<User> findByName1(String userName);

    /**
     * 模糊查询, 这儿不需要提供百分号, 例: 王
     * 了解, 一般不这样开发, 防止sql注入
     * 打印的sql语句: select * from user where username like '%王%'
     */
    @Deprecated
    List<User> findByName2(String userName);

    //查询总用户数
    int findTotal();

    //根据queryVo中的条件查询用户
    List<User> findUserByVo(QueryVo vo);


    /**
     * 根据传入参数条件查询
     */
    @Deprecated
    List<User> findUserByCondition1(@Param("user") User user);
    List<User> findUserByCondition2(@Param("user") User user);

    /**
     * 根据多个id查询Users
     */
    List<User> findUserByIds(@Param("vo") QueryVo vo);

    UserAccounts findUserDelay(int id);
}
