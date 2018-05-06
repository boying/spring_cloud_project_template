package com.jzw.common.bean;

/**
 * Created by boying on 2018/5/6.
 */
public class BaseResponse<T> {
    public static final String SUCCESS = "0000";
    public static final String FAILED = "0001";

    private String code;
    private String message;
    private T data;

    public BaseResponse() {
    }


    public BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(SUCCESS, message, data);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(SUCCESS, "", data);
    }

    public static <T> BaseResponse<T> fail(String message, T data) {
        return new BaseResponse<>(FAILED, message, data);
    }

    public static <T> BaseResponse<T> fail(T data) {
        return new BaseResponse<>(FAILED, "", data);
    }

    public boolean isSuccess(){
        return SUCCESS.equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
