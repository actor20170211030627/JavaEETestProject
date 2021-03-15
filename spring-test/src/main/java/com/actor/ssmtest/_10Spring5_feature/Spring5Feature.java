package com.actor.ssmtest._10Spring5_feature;

import java.lang.reflect.Method;

/**
 * https://www.bilibili.com/video/BV1mE411X7yp?p=158
 *  Spring5新特性的介绍
 *
 * 1.与JDK相关的升级
 *   1.1.jdk 版本要求:
 *     Spring5.0 在2017年9月发布了它的GA(通用)版本. 该版本是基于jdk8编写的, 所以jdk8以下版本将我发使用. 同时, 可以兼容jdk9版本.
 *
 *   1.2.利用jdk8版本更新的内容
 *     第一:基于JDK8的反射增强, 请看下面的代码
 *
 * 2.核心容器的更新
 *
 * 3.JetBrains Kotlin 语言支持
 *
 * 4.响应式编程风格
 *
 * 5.Junit5 支持
 *
 * 6.依赖类库的更新
 */
public class Spring5Feature {

    //循环次数定义: 10亿次
    private static final int LOOP_COUNT = 1000 * 1000 * 1000;

    public static void main(String[] args) throws Exception {
        //输出jdk的版本
        System.out.printf("java.version = %s\n", System.getProperty("java.version"));
        t1();
        t2();
        t3();
        /**
         * java.version = 1.7.0_72
         * 循环10亿次创建对象的时间: 6212ms
         * 循环10亿次给同一对象赋值的时间: 3035ms
         * 循环10亿次反射创建对象的时间: 290400ms
         *
         * java.version = 1.8.0_271
         * 循环10亿次创建对象的时间: 5ms
         * 循环10亿次给同一对象赋值的时间: 37ms
         * 循环10亿次反射创建对象的时间: 3431ms
         */
    }
    //每次重新生成对象
    public static void t1() {
        long s = System.currentTimeMillis();
        for (int i = 0; i < LOOP_COUNT; i ++) {
            Person p = new Person();
            p.setAge(i);
        }
        long e = System.currentTimeMillis();
        System.out.printf("循环10亿次创建对象的时间: %dms\n", e - s);
    }
    //同一个对象
    public static void t2() {
        long s = System.currentTimeMillis();
        Person p = new Person();
        for (int i = 0; i < LOOP_COUNT; i ++) {
            p.setAge(i);
        }
        long e = System.currentTimeMillis();
        System.out.printf("循环10亿次给同一对象赋值的时间: %dms\n", e - s);
    }
    //使用反射创建对象
    public static void t3() throws Exception {
        long s = System.currentTimeMillis();
        Class<Person> c = Person.class;
        Person p = c.newInstance();
        Method m = c.getMethod("setAge", int.class);
        for (int i = 0; i < LOOP_COUNT; i ++) {
            m.invoke(p, i);
        }
        long e = System.currentTimeMillis();
        System.out.printf("循环10亿次反射创建对象的时间: %dms\n", e - s);
    }
    static class Person {
        private int age = 20;
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }
}
