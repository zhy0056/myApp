package com.zhy.myapp_usercenter.exception;

import com.zhy.myapp_usercenter.common.CommonResponse;
import com.zhy.myapp_usercenter.common.ErrorCode;
import com.zhy.myapp_usercenter.common.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MyAppException.class)
    public CommonResponse<?> myAppExceptionHandler(MyAppException e){
        log.error("MyAppException: " + e.getMessage(), e);
        return ResponseUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }


    @ExceptionHandler(RuntimeException.class)
    public CommonResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResponseUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }


}
