package com.actor.javaeetestproject.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 类的描述
 * Copyright  : Copyright (c) 2020
 * Company    : 重庆酷川科技有限公司 http://www.kuchuanyun.com
 * Author     : 李大发
 * Date       : 2020/2/23 on 17:17
 */
@Configuration
@MapperScan("com.springboot.mybatis.plus.mapper*")
public class MybatisConfiguration {

    /**
     *
     * 分页组件,自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}