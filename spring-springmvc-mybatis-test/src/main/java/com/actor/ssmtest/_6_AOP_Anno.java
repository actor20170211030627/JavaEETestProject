package com.actor.ssmtest;

import com.actor.ssmtest.config.SpringConfiguration_AOP_Anno;
import com.actor.ssmtest.domain._5_Aop_Bean;
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
 * 应该使用环绕通知:
 *      @see org.aspectj.lang.annotation.Around
 *
 * 具体见: {@link com.actor.ssmtest.utils.Anno_Logger_Anno}
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=141
 *  总结和作业安排
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
