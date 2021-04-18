package com.actor.springmvc_test.exception;

/**
 * description: 自定义异常类
 *
 * @author : 李大发
 * date       : 2021/4/18 on 20
 * @version 1.0
 */
public class CustomException extends Exception {

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
