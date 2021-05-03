package com.actor.ssmtest._2Test_IOC_DI_Anno;

import com.actor.ssmtest.utils.JacksonUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 注解注入 测试
 */

//@Component(value="customer3Anno")  //将当前类对象存入spring容器中, value设置id的值(可以不写, 默认值就是类别首字母小写)
//@Component("customer3Anno")        //value 可以不写
@Component                           //默认值就是类别首字母小写: customer3Anno
public class Customer3Anno {

    private Long id;
    private String name;
    private Integer age;
    private Date birthday;

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
