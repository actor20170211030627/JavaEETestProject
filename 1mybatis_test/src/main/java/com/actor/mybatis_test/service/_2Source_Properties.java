package com.actor.mybatis_test.service;

/**
 * description: <resultMap & 源码解析 & properties标签的使用及细节
 *
 * date       : 2021/5/4 on 23
 * @version 1.0
 */
public class _2Source_Properties {

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=28
     * Mybatis中的返回值深入-调整实体类属性解决增和改方法的报错
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=29
     * Mybatis中的返回值深入-解决实体类属性和数据库列名不对应的两种方法
     *  1.在 IUserDao.xml 中配置查询结果的列名和实体类的属性名的对应关系
     *    <resultMap id="userMap" type="com.actor.mybatis_test.domain.User">
     *  2.将查询/新增/修改等语句的 resultType 改成 resultMap="userMap"
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=30
     * Mybatis中编写dao实现类的使用方式-查询列表(自己写dao的实现类, 不用代理, 了解)
     * https://www.bilibili.com/video/BV1mE411X7yp?p=31
     * Mybatis中编写dao实现类的使用-保存操作(了解)
     * https://www.bilibili.com/video/BV1mE411X7yp?p=32
     * Mybatis中编写dao实现类的使用-修改删除等其他操作(了解)
     * https://www.bilibili.com/video/BV1mE411X7yp?p=33
     * Mybatis中使用Dao实现类的执行过程分析-查询方法1
     * https://www.bilibili.com/video/BV1mE411X7yp?p=34
     * Mybatis中使用Dao实现类的执行过程分析-查询方法(https://blog.csdn.net/weixin_40631151/article/details/106250400)
     * https://www.bilibili.com/video/BV1mE411X7yp?p=35
     * Mybatis中使用Dao实现类的执行过程分析-增删改方法(http://t.zoukankan.com/wangjunwei-p-11313263.html)
     * https://www.bilibili.com/video/BV1mE411X7yp?p=36
     * Mybatis中使用代理Dao的执行过程分析
     */
    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=37
     * properties标签的使用及细节
     * <properties resource="jdbcConfig.properties"/>
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=38
     * typeAliases 标签和 package 标签
     *
     * <!-- p28 配置别名, 只能配置 domain 中类的别名: https://www.bilibili.com/video/BV1mE411X7yp?p=38 -->
     * <typeAliases>
     *     <!-- 1.系统已经配置了一些别名, 例: -->
     *     <!-- <typeAlias type="java.lang.Integer" alias="int"/> -->
     *     <!-- <typeAlias type="java.lang.Integer" alias="Integer"/> -->
     *
     *     <!-- 2.自定义别名, 这样在 IUserDao.xml 中可写: user / uSeR / USER / com.xx.domain.User, 不区分大小写 -->
     *     <!-- <typeAlias type="com.actor.mybatis_test.domain.User" alias="user"/> -->
     *
     *     <!-- 3.指定要配置别名的包, 当指定后, 该包下的实体类都会注册别名, 类名=别名, 并且别名不区分大小写 -->
     *     <package name="com.actor.mybatis_test.domain"/>
     * </typeAliases>
     *
     * 使用示例:
     * <resultMap id="userMap" type="uSeR">
     *
     * <!-- package 标签用于指定 dao接口 所在的包, 当指定完了之后, 就不需要写 mapper 以及 resource 或者 class 了
     *      1.XxxDao.xml和XxxDao.java必须同名, 否则报错: 某个方法找不到
     *  -->
     * <mappers>
     *     <package name="com.actor.mybatis_test.dao"/>
     * </mappers>
     */
}
