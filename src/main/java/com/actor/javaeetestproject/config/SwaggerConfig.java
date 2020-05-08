package com.actor.javaeetestproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description: Swagger配置, 访问地址:
 *      http://localhost:8080/swagger-ui.html
 *      http://localhost:8080/doc.html (swagger增强版)
 *
 * Author     : 李大发
 * Date       : 2020/2/23 on 15:54
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.actor.javaeetestproject.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("JavaEE测试项目 Manage Swagger RESTful APIs")
                .description("JavaEE测试项目 Swagger API 服务")
                .termsOfServiceUrl("http://swagger.io/")
                .contact(new Contact("李大发", "https://github.com/actor20170211030627", "1455198886@qq.com"))
                .version("1.0")
                .build();

    }

}
