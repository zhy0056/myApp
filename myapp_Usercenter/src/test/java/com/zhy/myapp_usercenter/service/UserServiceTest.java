package com.zhy.myapp_usercenter.service;
import java.util.Date;

import com.zhy.myapp_usercenter.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import com.zhy.myapp_usercenter.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
/**

用户服务测试

*/

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

//    @Test
//    public void testAddUser(){
//        User user = new User();
//        user.setUsername("zhy");
//        user.setUserAccount("zhy200056");
//        user.setAvatar("https://www.csdn.net/?spm=1001.2101.3001.4476");
//        user.setGender(0);
//        user.setUserPassword("103766");
//        user.setPhone("15602983197");
//        user.setEmail("zhanghaiyang200056@163.com");
//        boolean result = userService.save(user);
//        System.out.println(user.getId());
//        Assertions.assertTrue(result);
//    }


//    @Test
//    void userRegister() {
//        String userAccount = "zhy_test2";
//        String userPassword = "zhy103766";
//        String checkPassword = "zhy103766";
//        userService.userRegister(userAccount, userPassword ,checkPassword);
        //可以使用如下代码优化测试过程，可以做到一次测试完成(利用错误返回值-1)：
//        String userAccount = "zhy_test";
//        String userPassword = "103766";
//        String checkPassword = "103766";
//        long result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userPassword = "103766";
//        userAccount = "zhy";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        接下来逐一更改校验条件进行校验


    }

//    @Test
//    void userLogin() {
//        String userAccount = "zhy_test2";
//        String userPassword = "ffffffffffff";
//        User result = userService.userLogin(userAccount, userPassword);
//        Assertions.assertEquals(null, result);
//        userPassword = "zhy103766";
//        result = userService.userLogin(userAccount, userPassword);
//        System.out.println(result.getId() + result.getUserAccount());
//    }
//}