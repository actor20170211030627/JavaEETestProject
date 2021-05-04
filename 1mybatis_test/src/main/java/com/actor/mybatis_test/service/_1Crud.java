package com.actor.mybatis_test.service;

import com.actor.mybatis_test.dao.IUserDao;
import com.actor.mybatis_test.domain.User;
import com.actor.mybatis_test.vo.QueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * description: mybatis课程介绍: https://www.bilibili.com/video/BV1mE411X7yp?p=1
 * Github:  https://github.com/mybatis/mybatis-3    7469★
 * 下载地址: https://github.com/mybatis/mybatis-3/releases
 * 官网:     https://mybatis.org/mybatis-3/zh/getting-started.html
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=2
 * 1.Mybatis介绍
 * MyBatis 本是apache的一个开源项目iBatis, 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。2013年11月迁移到Github。
 *      MyBatis是一个优秀的持久层框架，它对jdbc的操作数据库的过程进行封装，使开发者只需要关注 SQL 本身，
 * 	而不需要花费精力去处理例如注册驱动、创建connection、创建statement、手动设置参数、结果集检索等jdbc繁杂的过程代码。
 * Mybatis通过xml或注解的方式将要执行的各种statement（statement、preparedStatemnt、CallableStatement）配置起来，
 * 并通过java对象和statement中的sql进行映射生成最终执行的sql语句，最后由mybatis框架执行sql并将结果映射成java对象并返回。
 * 使用ORM思想实现了结果集的封装, ORM: Object Relational Mapping(对象关系映射)
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=3
 * 1.1.持久层技术解决方案
 * 	JDBC技术:
 * 	    Connection
 * 	    PreparedStatement
 * 	    ResultSet
 * 	Spring的JdbcTemplate:
 * 	    Spring中对jdbc的简单封装
 * 	Apache的DBUtils:
 * 	    它和Spring的 JdbcTemplate 很像, 也是对Jdbc的简单封装
 *
 * 	以上这些都不是框架, JDBC是规范, Spring的JdbcTemplate和Apache的DBUtils都只是工具类
 *
 * 	https://www.bilibili.com/video/BV1mE411X7yp?p=4
 * 	https://www.bilibili.com/video/BV1mE411X7yp?p=5
 * 	mybatis环境搭建-前期准备
 * 	CREATE DATABASE IF NOT EXISTS `mybatis_test`;
 *  USE `mybatis_test`;
 *
 *  CREATE TABLE IF NOT EXISTS `user` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `username` varchar(32) NOT NULL COMMENT '用户名称',
 *   `birthday` datetime DEFAULT NULL COMMENT '生日',
 *   `sex` char(1) DEFAULT NULL COMMENT '性别',
 *   `address` varchar(256) DEFAULT NULL COMMENT '地址',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户';
 *
 * INSERT INTO `user` (`id`, `username`, `birthday`, `sex`, `address`) VALUES
 * 	(1, '老王', '2018-02-27 22:52:29', '男', '北京'),
 * 	(2, '小二王', '2018-02-27 22:52:29', '女', '北京金燕龙'),
 * 	(3, '小二王', '2018-02-27 22:52:29', '女', '北京金燕龙'),
 * 	(4, '传智播客', '2018-02-27 22:52:29', '男', '背景金燕龙'),
 * 	(5, '老王', '2018-02-27 22:52:29', '男', '北京'),
 * 	(6, '小马宝莉', '2018-02-27 22:52:29', '女', '北京修正');
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=6
 * mybatis的环境搭建
 * 1.在resources目录新建 SqlMapConfig.xml
 *   写User实体, 写IUserDao, 写IUserDao.xml(映射配置文件)
 *
 * 环境搭建的注意事项: https://www.bilibili.com/video/BV1mE411X7yp?p=7
 *
 * date       : 2021/5/3 on 22
 * @version 1.0
 */
public class _1Crud {
    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void close() throws IOException {
        //6.释放资源
        session.close();
        in.close();
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=8
     * mybatis的入门
     * 1.在SqlMapConfig.xml中配置:
     * <configuration>
     *     <!-- 配置环境 -->
     *     <environments default="mysql">
     *         <!-- 配置mysql的环境 -->
     *         <environment id="mysql">
     *             <!-- 配置事务类型 -->
     *             <transactionManager type="JDBC"/>
     *             <!-- 配置数据源 -->
     *             <dataSource type="POOLED">
     *                 <!-- 配置连接数据库的基本信息 -->
     *                 <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
     *                 <property name="url" value="jdbc:mysql://localhost:3306/mybatis_test?characterEncoding=UTF-8&amp;serverTimezone=UTC"/>
     *                 <property name="username" value="root"/>
     *                 <property name="password" value="123456"/>
     *             </dataSource>
     *         </environment>
     *     </environments>
     *
     *     <!-- 指定映射配置文件位置, 映射配置文件指的是每个dao独立的配置文件 -->
     *     <mappers>
     * <!--        <mapper resource="com/actor/mybatis_test/dao/IUserDao.xml"/>-->
     *         <!-- 1.使用mapper形式: 如果XxxDao.xml和XxxDao.java同名, 也可这样写 -->
     *         <mapper class="com.actor.mybatis_test.dao.IUserDao"/>
     *     </mappers>
     * </configuration>
     *
     * 2.在IUserDao.xml中配置:
     * <mapper namespace="com.actor.mybatis_test.dao.IUserDao">
     *     <!-- 配置查询所有-->
     *     <select id="findAll" resultType="com.actor.mybatis_test.domain.User">
     *         select * from user
     *     </select>
     * </mapper>
     */
    @Test
    public void findAll() {
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=9
     * mybatis'注解'开发和编写'dao实现类'的方式
     * 1. @Select("select * from user")
     *    List<User> findAll();
     * 2. <mapper class="com.actor.mybatis_test.dao.IUserDao"/>
     * 3. 不能要 IUserDao.xml 这个配置文件
     */
    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=10
     * mybatis入门案例中的设计模式分析
     * https://www.bilibili.com/video/BV1mE411X7yp?p=11
     * 自定义Mybatis的分析-执行查询所有分析
     * https://www.bilibili.com/video/BV1mE411X7yp?p=12
     * 自定义Mybatis的分析-创建代理对象的分析
     * https://www.bilibili.com/video/BV1mE411X7yp?p=13
     * 自定义mybatis的编码-根据测试类中缺少的创建接口和类
     * https://www.bilibili.com/video/BV1mE411X7yp?p=14
     * 自定义mybatis的编码-解析XML的工具类介绍
     * https://www.bilibili.com/video/BV1mE411X7yp?p=15
     * 自定义Mybatis的编码-创建两个默认实现类并分析类之间的关系
     * https://www.bilibili.com/video/BV1mE411X7yp?p=16
     * 自定义Mybatis的编码-实现基于XML的查询所有操作
     * https://www.bilibili.com/video/BV1mE411X7yp?p=17
     * 自定义Mybatis的编码-实现基于注解配置的查询所有操作
     * https://www.bilibili.com/video/BV1mE411X7yp?p=18
     * 今日课程内容介绍
     * https://www.bilibili.com/video/BV1mE411X7yp?p=19
     * 回顾自定义mybatis的流程分析
     * https://www.bilibili.com/video/BV1mE411X7yp?p=20
     * 基于注解的自定义再分析
     * https://www.bilibili.com/video/BV1mE411X7yp?p=21
     * 回顾Mybatis的环境搭建-实现查询所有功能
     * https://www.bilibili.com/video/BV1mE411X7yp?p=22
     * Mybatis的CRUD-保存操作
     */
    @Test
    public void saveUser() {
        User user = new User();
        user.userAge = 23;
        user.userName = "添加User";
        user.userAddress = "上海市";
        user.userSex = "女";
        user.userBirthday = new Date();
        userDao.saveUser(user);
        //提交事务, 否则不能存入数据库
        session.commit();
        System.out.println("保存成功:" + user);
    }
    @Test
    public void saveUserReturnId() {
        User user = new User();
        user.userAge = 23;
        user.userName = "测试保存User, 返回id";
        user.userAddress = "上海市";
        user.userSex = "男";
        user.userBirthday = new Date();
//        int result = userDao.saveUserReturnId1(user);//1.保存User, 返回id
//        int result = userDao.saveUserReturnId2(user);//2.当参数有 @Param 注解
//        int result = userDao.saveUserReturnId3(user);//3.使用 useGeneratedKeys="true" 这种类型, 返回的id类型可以是任意类型??? 待验证
        int result = userDao.saveUserReturnId_Anno(user);//4.保存User, 使用 @SelectKey 返回id
        session.commit();
        System.out.printf("保存结果=%b, user=%s\n", result == 1, user);
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=23
     * Mybatis的CRUD-修改和删除操作
     */
    @Test
    public void updateUser() {
        Integer id = 9;
        User user = userDao.findById(id);
        if (user == null) {
            System.out.printf("未找到id=%d 的User\n", id);
            return;
        }
        user.userAge = 33;
        user.userName = "更新User";
        user.userAddress = "上海市更新";
        user.userSex = "男";
        user.userBirthday = new Date();
        userDao.updateUser(user);
        //提交事务, 否则不能存入数据库
        session.commit();
        System.out.println("更新成功" + user);
    }
    @Test
    public void deleteUser() {
        Integer id = 9;
        userDao.deleteUser(id);
        //提交事务, 否则不能存入数据库
        session.commit();
        System.out.println("删除成功");
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=24
     * Mybatis的CRUD-查询一个和模糊查询
     */
    @Test
    public void findById() {
        User user = userDao.findById(5);
        System.out.printf("查询结果: %s\n", user);
    }
    @Test
    public void findByName() {
        /**
         * 打印的sql语句:
         * Preparing: select * from user where username like ?
         * Parameters: %王%(String)
         * 分析: 使用 PrepatedStatement 的参数占位符
         */
        List<User> users1 = userDao.findByName1("%王%");//1.模糊查询, 这儿需要提供百分号, 例: "%王%"
        System.out.println("findByName1方法:");
        for (User user : users1) {
            System.out.println(user);
        }

        /**
         * https://www.bilibili.com/video/BV1mE411X7yp?p=25
         * 打印的sql语句:
         * Preparing: select * from user where username like '%王%'
         * Parameters:
         * 分析: 使用 Statement 对象的字符串拼接SQL
         */
        List<User> users2 = userDao.findByName2("王");//2.不需要提供百分号, 一般不这样开发, 防止sql注入, 例: "王"
        System.out.println("findByName1方法:");
        for (User user : users2) {
            System.out.println(user);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=25
     * Mybatis的CRUD-查询返回一行一列和占位符分析
     */
    @Test
    public void findTotal() {
        int total = userDao.findTotal();
        System.out.printf("查询结果: total=%d\n", total);
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=26
     * Mybatis的CRUD-保存操作的细节-获取保存数据的id
     * @see #saveUserReturnId()
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=27
     * Mybatis中参数的深入-使用实体类的包装对象作为查询条件
     * OGNL表达式:
     *      Object Graphic Navigation Language(对象图导航语言)
     *  它是根据对象的取值方法来获取数据. 在写法上把get省略了.
     *  比如: 获取用户名称
     *      类中写法: user.getUsername()
     *      OGNL表达式写法: user.username
     *  mybatis中直接写 username, 因为在parameterType中已经提供了属性所属的类,
     */
    @Test
    public void findUserByVo() {
        QueryVo vo = new QueryVo();
        vo.user = new User();
        vo.user.userName = "%王%";
        List<User> users = userDao.findUserByVo(vo);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
