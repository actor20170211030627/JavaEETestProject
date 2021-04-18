package com.actor.springmvc_test.domain;

import com.actor.springmvc_test.utils.JacksonUtils;

/**
 * description: 描述
 *
 * @author : 李大发
 * date       : 2021/4/17 on 13
 * @version 1.0
 */
public class BaseResponse<T> {

    int code = 200;
    String message = "成功";
    T data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseResponse<Object> ok() {
        return new BaseResponse<>();
    }

    public static <T> BaseResponse<Object> ok(T data) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.data = data;
        return response;
    }

    public static BaseResponse<Object> ok(String message) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<Object> ok(String message, T data) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = message;
        response.data = data;
        return response;
    }

    public static BaseResponse<Object> error(int code, String message) {
        return new BaseResponse<>(code, message);
    }

    public static <T> BaseResponse<Object> error(int code, String message, T data) {
        return new BaseResponse<>(code, message, data);
    }

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
