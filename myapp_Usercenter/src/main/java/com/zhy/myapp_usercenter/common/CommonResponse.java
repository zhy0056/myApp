package com.zhy.myapp_usercenter.common;

import lombok.Data;

import java.io.Serializable;


@Data
public class CommonResponse<T> implements Serializable {

    private int code;

    private T data;

    private String msg;

    private String description;

    public CommonResponse(int code, T data, String msg, String description) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.description = description;

    }

    public CommonResponse(int code, T data, String msg) {
        this(code, data, msg, "");
    }

    public CommonResponse(int code, T data) {
        this(code, data, "", "");
    }

    public CommonResponse(ErrorCode errorCode){
        this(errorCode.getCode(), null, errorCode.getMsg(), errorCode.getDescription());
    }

}
