package com.actor.ssmtest._6AOP_Anno;

import com.actor.ssmtest._5AOP_Xml._5_Aop_Bean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * description:
 * https://www.bilibili.com/video/BV1mE411X7yp?p=140
 *  spring基于注解的 AOP 配置
 *  @see org.springframework.context.annotation.EnableAspectJAutoProxy
 *      配置spring开启注解 AOP 的支持, 也可在xml中配置开启: <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 *
 *  @see org.aspectj.lang.annotation.Aspect
 *      在切面类中添加, 表示当前类是一个切面类
 *
 *  注意:
 *      不能使用下方的4中通知的注解, 因为spring的调用通知顺序有问题(最后2个通知, 先调 最终通知, 再调后置通知/异常通知)
 *      @see org.aspectj.lang.annotation.Before
 *      @see org.aspectj.lang.annotation.AfterReturning
 *      @see org.aspectj.lang.annotation.AfterThrowing
 *      @see org.aspectj.lang.annotation.After
 *      高版本好像已经修复了这个通知, 例5.3.3???
 *
 * 应该使用环绕通知:
 *      @see org.aspectj.lang.annotation.Around
 *
 * 具体见: {@link Anno_Logger_Anno}
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=141
 *  总结和作业安排
 *
 * 作业解答(下方3个视频链接):
 * https://www.bilibili.com/video/BV1mE411X7yp?p=148
 *  基于XML的AOP实现事务控制
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=149
 *  基于注解的AOP实现事务控制及问题分析_上
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=150
 *  基于注解的AOP实现事务控制及问题分析_下
 *
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */

public class _6_AOP_Anno {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration_AOP_Anno.class);
        _5_Aop_Bean aop_bean = ac.getBean("_5_Aop_Bean", _5_Aop_Bean.class);

        testAop(aop_bean);
    }

    //测试AOP切面
    public static void testAop(_5_Aop_Bean aop_bean) {
        aop_bean.saveAccount();
        aop_bean.updateAccount(1);
        aop_bean.deleteAccount();
    }
}
