package com.rabbit.java.common.result;


import com.rabbit.java.common.constant.Constants;

public class CommonResult<T> {
    private Integer code = Constants.SUCCESS_CODE;
    private T msg;

    public CommonResult() {
        super();
    }

    public CommonResult(Integer code, T msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult(Constants.SUCCESS_CODE, Constants.OK);
    }

    public static <T> CommonResult<T> success(T msg) {
        return new CommonResult(Constants.SUCCESS_CODE, msg);
    }

    public static <T> CommonResult<T> failed(Integer code, T msg) {
        return new CommonResult(code, msg);
    }

    public static <T> CommonResult<T> failed(CommonErrorMessage message) {
        return new CommonResult(message.getCode(), message.getMsg());
    }

    public static CommonResult failed(Integer code) {
        return new CommonResult(code, "");
    }

    public static <T> CommonResult<T> failed() {
        return new CommonResult(Constants.ERROR_CODE, Constants.ERR);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}
