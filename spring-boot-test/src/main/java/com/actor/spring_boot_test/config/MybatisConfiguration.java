package com.actor.spring_boot_test.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 连接数据库的持久层框架
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