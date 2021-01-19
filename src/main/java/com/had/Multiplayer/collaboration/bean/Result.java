package com.had.Multiplayer.collaboration.bean;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description TODO
 * @Author had
 * @Date 2021/1/11 14:31
 * @Version 1.0
 **/
public class Result<T> implements Serializable {
    private String status;
    private String msg;
    private T data;
    private Boolean isLogin;

    public Result() {
    }

    public static <T> Result successResult(String status, T t, Boolean isLogin) {
        return successResult(status, null, t, isLogin);
    }

    public static <T> Result successResult(String status, Boolean isLogin) {
        return successResult(status, null, null, isLogin);
    }

    public static <T> Result successResult(String status, String msg) {
        return successResult(status, msg, null, null);
    }

    public static <T> Result successResult(String status, String msg, T t) {
        return successResult(status, msg, t, null);
    }

    public static <T> Result successResult(String status, String msg, T t, Boolean isLogin) {
        return new Result(status, msg, t, isLogin);
    }

    public static Result failResult(String msg) {
        return new Result("fail", msg);
    }

    public Result(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(String status, T data, Boolean isLogin) {
        this.status = status;
        this.data = data;
        this.isLogin = isLogin;
    }

    public Result(String status, Boolean isLogin) {
        this.status = status;
        this.isLogin = isLogin;
    }

    public Result(String status, String msg, T data, Boolean isLogin) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.isLogin = isLogin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
