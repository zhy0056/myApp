package com.zhy.myapp_usercenter.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String userAccount;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 
     */
    private Integer gender;

    /**
     * 
     */
    private String userPassword;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String email;

    /**
     * 0:正常
     */
    private Integer userStatus;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;


    /**
     * 0:未删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     *用户权限(身份)，0->普通用户
     */
    private int authority;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}