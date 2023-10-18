package com.zhy.myapp_usercenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
class MyappUsercenterApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Test
//    void testEncrypt() {
//        String salt = "zhang2000hai05yang06";
//        String encodedPassword = DigestUtils.md5DigestAsHex(
//                (salt + salt + "1037663815" + salt + salt).getBytes());
//        System.out.println(encodedPassword);
//    }

}
