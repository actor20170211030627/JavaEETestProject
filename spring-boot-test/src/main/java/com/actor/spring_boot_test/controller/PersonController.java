package com.actor.spring_boot_test.controller;

import com.actor.spring_boot_test.domain.BaseResult;
import com.actor.spring_boot_test.domain.Person;
import com.actor.spring_boot_test.service.PersonService;
import com.actor.spring_boot_test.viewmodel.PersonDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * description: 类的描述
 * date       : 2020/2/25 on 11:49
 */
//@Controller
@RestController //包含 @Controller & @ResponseBody
@RequestMapping("/person")
@Api(value = "Person测试", description = "Person相关接口")
public class PersonController {

    @Autowired
    private PersonService personService;

    //增, @RequestBody: 以json形式上传参数
    @PostMapping("/insert")
    @ApiOperation(value = "增加Person")
    public BaseResult<Integer> insert(@RequestBody PersonDto personDto) {
        return personService.insert(personDto);
    }

    //删, @RequestParam: 传入的值key = id, 必传
    @DeleteMapping("/deleteById")
    @ApiOperation("根据id删除Person")
    public BaseResult<Integer> deletePersonById(@RequestParam(value = "id", required = true) long id111) {
        return personService.deletePersonById(id111);
    }

    //改
    @PostMapping("/update")
    @ApiOperation("更新Person")
    public BaseResult<Integer> updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    //改, 头像和参数一起上传
    @PostMapping("/update_head")
    @ApiOperation("更新头像")
    public BaseResult<Integer> updatePersonHead(@RequestParam("file") MultipartFile files, Person person) {
        return personService.updatePerson(person);
    }

    //查
    @GetMapping("/getById")
    @ApiOperation(value = "根据id获取Person")
    public BaseResult<Person> getPersonById(long id) {
        return personService.getPersonById(id);
    }

    //根据name模糊查询
    @GetMapping("/pagePerson")
    @ApiOperation("根据name查询Person列表")
    public BaseResult<IPage<Person>> getPersonsByName(long page, long size, String name) {
        return personService.getPersonsByName(page, size, name);
    }
}
