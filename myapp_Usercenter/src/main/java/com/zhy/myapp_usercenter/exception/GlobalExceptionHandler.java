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
    public CommonResponse<?> myAppExceptionHandler(MyAppException myAppException){
        log.error("MyAppException: " + myAppException.getMessage(), myAppException);
        return ResponseUtils.error(myAppException.getCode(), myAppException.getMessage(), myAppException.getDescription());
    }


    @ExceptionHandler(RuntimeException.class)
    public CommonResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResponseUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }


}
