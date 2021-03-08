package com.actor.ssmtest.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component  //添加到spring容器中
@Aspect     //表示当前类是一个切面类
public class Anno_Logger_Anno {

    @Pointcut("execution(* com.actor.ssmtest.domain.*.*(..))")
    private void pt1() {
    }

    /**
     * ★★★不能使用下方的4中通知的注解, 因为spring的调用通知顺序有问题(最后2个通知, 先调 最终通知, 再调后置通知/异常通知)
     */
//    @Before("pt1()")
    public void beforePrintLog() {
        System.out.println("1.前置通知: Anno_Logger_Anno.beforePrintLog()");
    }

//    @AfterReturning("pt1()")
    public void afterPrintLog() {
        System.out.println("2.后置通知: Anno_Logger_Anno.afterPrintLog()");
    }

//    @AfterThrowing("pt1()")
    public void afterThrowingLog() {
        System.out.println("3.异常通知: Anno_Logger_Anno.afterThrowingLog()");
    }

//    @After("pt1()")
    public void afterFinallyPrintLog() {
        System.out.println("4.最终通知: Anno_Logger_Anno.afterFinallyPrintLog()");
    }

    //环绕通知, 调用顺序没有问题
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        try {
            Object[] args = pjp.getArgs();//方法执行时的参数

            System.out.println("1.前置通知: 目标方法执行前执行");

            Object rtValue = pjp.proceed(args);//方法执行结果

            System.out.println("2.后置通知: 目标方法执行完后执行");
            return rtValue;
//        } catch(Exception e) {//Exception 拦不住
        } catch(Throwable t) {
            System.out.println("3.异常通知: 目标方法执行过程中出现异常后执行");
            throw new RuntimeException(t);
        } finally {
            System.out.println("4.最终通知: 无论切入点方法是否正常执行, 它都会在其后面执行");
        }
    }
}
