package com.zhy.myapp_usercenter.exception;

import com.zhy.myapp_usercenter.common.ErrorCode;

public class MyAppException extends RuntimeException{


    /**
     * 错误码
     */
    private final int code;

    /**
     * 描述
     */
    private final String description;

    public MyAppException(String msg, int code, String description) {
        super(msg);
        this.code = code;
        this.description = description;
    }

    public MyAppException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public MyAppException(ErrorCode errorCode, String description) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }


    public String getDescription() {
        return description;
    }





}
