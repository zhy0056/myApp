package com.zhy.myapp_usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhy.myapp_usercenter.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
* @author zhy
* @description 针对表【user(用户信息表)】的数据库操作Service
* @createDate 2023-10-03 19:13:48
*/
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userAccount 用户账户，可以自定义
     * @param userPassword 用户密码
     * @param checkPassword 用户二次确认密码
     * @return 用户id（分配）
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     *
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     *
     * @param userAccount
     * @param userPassword
     * @return
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

//    /**
//     *
//     * @param username
//     * @return
//     */
////    List<User> userQuery(String username);

//
//
//    boolean userDelete(long id);

}
