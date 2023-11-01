package com.zhy.myapp_usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体,封装一个对象记录所有的请求参数
 */
@Data
public class UserRegisterRequest implements Serializable {


    private static final long serialVersionUID = 8523569899675918787L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

    private String name;

}
