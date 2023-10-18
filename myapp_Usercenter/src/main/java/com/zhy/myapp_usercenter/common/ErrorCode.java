package com.zhy.myapp_usercenter.common;

public enum ErrorCode {

    SUCCESS(0,"请求正常", ""),
    REQUEST_VALUE_ERROR(4000,"请求参数错误", ""),
    REQUEST_VALUE_NULL_ERROR(4001,"请求参数缺失", ""),
    PARAMS_NULL_ERROR(4002, "请求的数据为空", ""),
    REQUEST_NULL_ERROR(4002, "未找到请求", ""),
    NOT_LOGIN(4003, "未登录",""),
    INVALID_AUTH(4004, "权限错误", ""),
    SYSTEM_ERROR(5000, "系统异常", ""),

    ;



    private final int code;

    private final String msg;

    private final String description;

    ErrorCode(int code, String msg, String description) {
        this.code = code;
        this.msg = msg;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getDescription() {
        return description;
    }
}
