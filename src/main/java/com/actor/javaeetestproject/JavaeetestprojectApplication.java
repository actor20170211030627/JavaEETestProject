package com.actor.javaeetestproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.actor.javaeetestproject.repository")//扫描Mapper对象, 并注入到Spring容器中
public class JavaeetestprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaeetestprojectApplication.class, args);
    }

}
