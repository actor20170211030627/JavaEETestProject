package com.actor.ssmtest._5AOP_Xml;

import org.aspectj.lang.ProceedingJoinPoint;

public class AOP_Logger_Xml {

    public void beforePrintLog() {
        System.out.println("1.前置通知: Logger_Xml.beforePrintLog()");
    }

    public void afterPrintLog() {
        System.out.println("2.后置通知: Logger_Xml.afterPrintLog()");
    }

    public void afterThrowingLog() {
        System.out.println("3.异常通知: Logger_Xml.afterThrowingLog()");
    }

    public void afterFinallyPrintLog() {
        System.out.println("4.最终通知: Logger_Xml.afterFinallyPrintLog()");
    }

    //环绕通知
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
