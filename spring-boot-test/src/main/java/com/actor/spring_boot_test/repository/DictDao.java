package com.actor.spring_boot_test.repository;

import com.actor.spring_boot_test.domain.Dict;
//import org.apache.ibatis.annotations.Select;

public interface DictDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    //@Select("select * from dict where id = ")
    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}