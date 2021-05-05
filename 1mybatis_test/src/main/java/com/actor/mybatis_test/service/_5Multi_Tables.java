package com.actor.mybatis_test.service;

import com.actor.mybatis_test.dao.IAccountDao;
import com.actor.mybatis_test.dao.IRoleDao;
import com.actor.mybatis_test.dao.IUserDao;
import com.actor.mybatis_test.domain.Account;
import com.actor.mybatis_test.domain.AccountUser;
import com.actor.mybatis_test.domain.Role;
import com.actor.mybatis_test.domain.RoleUsers;
import com.actor.mybatis_test.domain.User;
import com.actor.mybatis_test.domain.UserAccounts;
import com.actor.mybatis_test.domain.UserRoles;
import com.actor.mybatis_test.dto.AccountDto;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * description: 多表关联查询
 *
 * date       : 2021/5/5 on 16
 * @version 1.0
 */
public class _5Multi_Tables {
    private InputStream in;
    private SqlSession session;
    private IRoleDao roleDao;
    private IAccountDao accountDao;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        roleDao = session.getMapper(IRoleDao.class);
        accountDao = session.getMapper(IAccountDao.class);
    }
    @After
    public void close() throws IOException {
        //6.释放资源
        session.close();
        in.close();
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=48
     * mybatis表之间关系分析
     *  一对多: 用户和订单, 1个用户可以有多个订单(订单表中添加用户的'外键')
     *  多对一: 订单和用户
     *  一对一: 人和身份证, 1个人只能有1个身份证号
     *  多对多: 老师和学生, 用户和角色. 1个老师可以教多个学生, 1个学生可以有多个老师
     *
     *  https://www.bilibili.com/video/BV1mE411X7yp?p=49
     *  完成Account表的建立及实现单表查询
     * @see Account
     */
    @Test
    public void findAll() {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=50
     * 完成account的一对一操作-通过写account的子类方式查询
     */
    //select a.*, u.id as uid, u.username, u.address from Account a, user u where a.uid = u.id;
    //查询所有Account, 附带User的 id, username, address
    @Test
    public void findAllAccount1() {
        List<AccountDto> accounts = accountDao.findAllAccount1();//返回 AccountDto
        for (AccountDto account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=51
     * 完成account一对一操作-建立实体类关系的方式
     * Account 里面绑定一个 User
     * 1.在<resultMap 中绑定User
     * <resultMap id="accountMap" type="com.actor.mybatis_test.domain.Account">
     *     <id property="id" column="id"/>
     *     <result property="uid" column="uid"/>
     *     <result property="money" column="money"/>
     *     <!-- 绑定User, uid: 通过uid获取.   javaType: 封装到哪个对象(可不写) -->
     *     <association property="user" column="uid" javaType="com.actor.mybatis_test.domain.User">
     *         <!-- 注意: 这儿要写uid, 不能写id, 否则查出来的是Account的<id column="id"/>的值... -->
     *         <id property="userId" column="uid"/>
     *         <result property="userAge" column="age"/>
     *         <result property="userName" column="username"/>
     *         <result property="userAddress" column="address"/>
     *         <result property="userSex" column="sex"/>
     *         <result property="userBirthday" column="birthday"/>
     *     </association>
     * </resultMap>
     *
     * 2.查询语句: select a.*, u.* from Account a, user u where a.uid = u.id;
     */
    //查询所有Account, 并把对应的User查出来
    @Test
    public void findAccount2() {
        List<AccountUser> accounts = accountDao.findAccount2();//返回 AccountUser
        for (AccountUser account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=52
     * 完成user的一对多查询操作
     * 1.在<resultMap 中绑定 List<Account>
     * <resultMap id="userMap" type="com.actor.mybatis_test.domain.User">
     *     <!-- 主键字段的对应, 如果实体是 userId, 对应的数据库是 id, 需要这样配 -->
     *     <id property="userId" column="id"/>
     *     <!-- 非主键字段的对应 -->
     *     <result property="userAge" column="age"/>
     *     <result property="userName" column="username"/>
     *     <result property="userAddress" column="address"/>
     *     <result property="userSex" column="sex"/>
     *     <result property="userBirthday" column="birthday"/>
     *     <!-- 配置User对象中account集合的映射, ofType: 集合类型 -->
     *     <collection property="accounts" ofType="com.actor.mybatis_test.domain.Account">
     *         <!-- 注意: 由于user数据库也有个id, 所以account这儿的column不能再写id, 应该配置查询语句成 renamedAccountId -->
     *         <id property="id" column="renamedAccountId"/>
     *         <id property="uid" column="uid"/>
     *         <id property="money" column="money"/>
     *     </collection>
     * </resultMap>
     *
     * 2.查询语句:
     * //这样查出来的account.id没值
     * select * from user u LEFT OUTER JOIN account a ON a.uid = u.id;
     *
     * <!-- 这儿account的id 要 as 成: renamedAccountId -->
     * select u.*, a.*, a.ID as renamedAccountId from user u LEFT OUTER JOIN account a ON a.uid = u.id;
     * //可去掉u.
     * select *, a.*, a.ID as renamedAccountId from user u LEFT OUTER JOIN account a ON a.uid = u.id;
     */
    //查询所有User列表, 并把每个User对应的所以Account一起查出来
    @Test
    public void findAllUsers1() {
        List<UserAccounts> users = accountDao.findAllUsers1();//返回 UserAccounts
        for (UserAccounts user : users) {
            System.out.println(user);
        }
    }

    //多表(用户和角色)
    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=53
     * 分析mybatis多对多的步骤并搭建环境
     * https://www.bilibili.com/video/BV1mE411X7yp?p=54
     * mybatis多对多准备角色表的实体类和映射配置(用户和角色)
     * @see Role 里面有建表sql
     */
    @Test
    public void findAllRoles() {
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=55
     * mybatis多对多操作-查询角色获取角色下所属用户信息
     *
     * 查询所有角色, 并返回角色对应的所有用户(包括中间表)
     * select * from role r LEFT OUTER JOIN user_role ur ON r.id = ur.rid
     * LEFT OUTER JOIN user u ON u.id = ur.uid
     * //查出来不包括中间表
     * select r.*, u.* from role r LEFT OUTER JOIN user_role ur ON r.id = ur.rid
     * LEFT OUTER JOIN user u ON u.id = ur.uid
     */
    //查出所有角色, 一个角色可包含多个用户
    @Test
    public void findAllRoleInfos() {
        List<RoleUsers> roles = roleDao.findAllRoleInfos(); //返回 RoleUsers
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=56
     * mybatis多对多操作-查询用户获取用户所包含的角色信息
     */
    //查出所有用户, 一个用户可包含多个角色
    @Test
    public void findAllUserInfos() {
        List<UserRoles> roles = roleDao.findAllUserInfos(); //返回 UserRoles
        for (UserRoles role : roles) {
            System.out.println(role);
        }
    }
}
