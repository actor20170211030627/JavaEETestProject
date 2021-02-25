package com.actor.spring_boot_test.repository;

import com.actor.spring_boot_test.domain.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * description: 使用MyBatis Plus后Mapper只要继承BaseMapper接口即可，
 *              即使不添加XML映射文件也可以实现该接口提供的增删改查功能，
 *              还可以配合Wrapper进行条件操作，当然这些操作都仅仅限于单表操作，
 *              一旦涉及多表联查，那么还是乖乖添加**Mapper.xml来自定义SQL吧！
 * author     : 李大发
 * date       : 2020/2/25 on 15:07
 */
@Repository
public interface PersonRepository extends BaseMapper<Person> {
}
