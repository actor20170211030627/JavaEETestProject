package com.actor.mybatis_test.dao;

import com.actor.mybatis_test.domain.UserAccounts;
import com.actor.mybatis_test.vo.QueryVo;
import com.actor.mybatis_test.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * description: 用户持久层接口
 * <p>
 * date       : 2021/5/4 on 08
 *
 * @version 1.0
 */
public interface IUserDao {

//    @Select("select * from user")
    List<User> findAll();

    void saveUser(User user);

    //保存User, 返回id
    int saveUserReturnId1(User user);

    //当参数有 @Param 注解
    int saveUserReturnId2(@Param("user") User user);

    //使用 useGeneratedKeys="true" 这种类型, 返回的id类型可以是任意类型??? 待验证
    int saveUserReturnId3(User user);

    /**
     * 保存User, 使用 @SelectKey 返回id
     * 注意: 1.经测试, 当使用 @SelectKey, 那么不能在 mapper.xml 中写<insert 语句, 否则user.id不能赋值.
     *        只能在方法上写注解插入: @Insert
     *       2.select last_insert_id() 必须先插入数据, 才能返回插入数据的id, 否则返回0
     */
    @Insert("insert into user(username, address, sex, birthday) values(#{user.username}, #{user.address}, #{user.sex}, #{user.birthday})")
    @SelectKey(statement="select last_insert_id()", keyProperty="user.id", before=false, resultType=int.class)
    int saveUserReturnId_Anno(@Param("user") User user);

    //更新User
    void updateUser(User user);

    void deleteUser(Integer id);

    User findById(Integer id);

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
