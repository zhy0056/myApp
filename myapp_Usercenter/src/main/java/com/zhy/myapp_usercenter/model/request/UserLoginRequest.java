package com.zhy.myapp_usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体,封装一个对象记录所有的请求参数
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 991031098125885368L;

    private String userAccount;

    private String userPassword;
}
