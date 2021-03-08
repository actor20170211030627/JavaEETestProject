package com.actor.ssmtest.domain;

import org.springframework.stereotype.Component;

//用来测试AOP
@Component
public class _5_Aop_Bean {

    //测试AOP切面
    public void saveAccount() {
        System.out.println("保存: saveAccount()");
    }

    public void updateAccount(int i) {
        System.out.println("更新: updateAccount(int i)");
    }

    public int deleteAccount() {
        System.out.println("删除: int deleteAccount()");
        int i = 1 / 0;
        return 1;
    }
}
