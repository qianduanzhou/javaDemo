package com.example.demo.dto;

public class ResponseResult<T> {
    private int code;       // 状态码
    private boolean success; // 是否成功
    private String msg;     // 消息
    private T data;         // 数据

    // 构造方法
    public ResponseResult(int code, boolean success, String msg, T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    // 静态方法：成功响应
    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<>(200, true, msg, data);
    }

    // 静态方法：失败响应
    public static <T> ResponseResult<T> fail(int code, String msg) {
        return new ResponseResult<>(code, false, msg, null);
    }
}