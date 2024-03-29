package com.rf.exception;


public enum CommonException{

    SYSTEM_EXCEPTION(-1,"系统繁忙,请稍后重试"),
    USER_NO_LOGIN(1,"用户未登录"),
    USER_LOGIN_TIMEOUT_EXCEPTION(2,"用户登录已过时");

    private Integer code;

    private String message;

    CommonException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
