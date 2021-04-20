package com.actor.spring_boot_test.domain;

import java.util.Locale;

/**
 * Description: 返回基类
 * Date       : 2020/2/23 on 15:28
 */
public class BaseResult<T> {

    public int code;
    public String msg;
    public T data;

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> BaseResult<T> resultOk() {
        return new BaseResult<>(200, "OK");
    }

    public static <T> BaseResult<T> resultOk(T data) {
        return new BaseResult<>(200, "OK", data);
    }

    public static <T> BaseResult<T> resultError(Exception e) {
        return new BaseResult<>(500, String.format(Locale.getDefault(), "Error: %s", e.getMessage()));
    }
}
