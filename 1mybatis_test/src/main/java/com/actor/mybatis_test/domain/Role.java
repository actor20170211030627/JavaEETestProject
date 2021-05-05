package com.actor.mybatis_test.domain;

import com.actor.mybatis_test.utils.JacksonUtils;

import java.io.Serializable;

/**
 * description: 角色
 * 1.创建角色表
 * CREATE TABLE `role` (
 * 	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
 * 	`ROLE_NAME` VARCHAR(30) NULL DEFAULT NULL COMMENT '角色名称' COLLATE 'utf8_general_ci',
 * 	`ROLE_DESC` VARCHAR(60) NULL DEFAULT NULL COMMENT '角色描述' COLLATE 'utf8_general_ci',
 * 	PRIMARY KEY (`id`) USING BTREE
 * )
 * COMMENT='角色'
 * COLLATE='utf8_general_ci'
 * ENGINE=InnoDB
 * AUTO_INCREMENT=3
 * ;
 *
 * 2.插入数据: INSERT INTO `role` VALUES (1, '院长', '管理整个学院'),(2, '总裁', '管理整个公司'),(3, '校长', '管理整个学校');
 *
 * 3.创建中间表
 * CREATE TABLE `user_role` (
 * 	`uid` INT(11) NOT NULL COMMENT '用户编号',
 * 	`rid` INT(11) NOT NULL COMMENT '角色编号',
 * 	INDEX `FK_user_role_user` (`uid`) USING BTREE,
 * 	INDEX `FK_user_role_role` (`rid`) USING BTREE,
 * 	CONSTRAINT `FK_user_role_role` FOREIGN KEY (`rid`) REFERENCES `mybatis_test`.`role` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
 * 	CONSTRAINT `FK_user_role_user` FOREIGN KEY (`uid`) REFERENCES `mybatis_test`.`user` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
 * )
 * COMMENT='用户和角色的中间表'
 * COLLATE='utf8_general_ci'
 * ENGINE=InnoDB
 * ;
 *
 * 4.插入数据: INSERT INTO `user_role` VALUES (6, 1),(5, 2),(5, 1);
 *
 * date       : 2021/5/5 on 21
 * @version 1.0
 */
public class Role implements Serializable {

    public Integer roleId;
    public String roleName;
    public String roleDesc;

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
