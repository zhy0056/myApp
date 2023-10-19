package com.zhy.myapp_usercenter.common;

public class ResponseUtils {
    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> success(T data){
        return new CommonResponse<>(0,data,"success", "请求执行成功");
    }

    /**
     * 以下为失败
     * @param errorCode
     * @return
     */
    public  static CommonResponse error(ErrorCode errorCode){
        return new CommonResponse<>(errorCode);
    }

    public  static CommonResponse error(ErrorCode errorCode, String msg, String description){
        return new CommonResponse(errorCode.getCode(), null, msg, description);
    }

    public  static CommonResponse error(int code, String msg, String description){
        return new CommonResponse(code, null, msg, description);
    }

    public  static CommonResponse error(ErrorCode errorCode, String description){
        return new CommonResponse(errorCode.getCode(), errorCode.getMsg(), description);
    }
}
