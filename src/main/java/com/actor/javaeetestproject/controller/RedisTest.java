package com.actor.javaeetestproject.controller;

import com.actor.javaeetestproject.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: Redis测试
 * Author     : 李大发
 * Date       : 2020/2/23 on 14:49
 */
@Controller
@RequestMapping("/redis")
@Api(value = "Redis测试", description = "Redis get/set测试")
public class RedisTest {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisTest(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    //1.String
    @GetMapping("/setString")
    @ResponseBody
    public BaseResult<Object> setString(@ApiParam(/*name = "key", */value = "redis的key") String key, @ApiParam(value = "redis的value") String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value/*, long timeout, TimeUnit unit*/);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.resultError(e);
        }
        return BaseResult.resultOk();
    }

    //2.hashMap 散列
    @GetMapping("/putMap")
    public void putMap(String key, Object mapKey, Object mapValue) {
        stringRedisTemplate.opsForHash().put(key, mapKey, mapValue);
    }

    //3.linkedList 列表
//    @GetMapping("/setList")
//    public void setList(String key, String value) {
//        stringRedisTemplate.opsForList().set(key, 1, value);
//    }

    //4.set 集合
    @GetMapping("/setSet")
    public void setSet(String key, String... values) {
        stringRedisTemplate.opsForSet().add(key, values);
    }

    //5.sort set 有序集合类型(根据权重, 权重越大越靠前)
//    @GetMapping("/setSortSet")
//    public void setSortSet(String key, String value) {
//        stringRedisTemplate.opsForZSet().add(key, value, 1D);
//    }
}
