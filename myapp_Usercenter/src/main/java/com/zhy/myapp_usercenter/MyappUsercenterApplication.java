package com.zhy.myapp_usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhy.myapp_usercenter.mapper")
public class MyappUsercenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappUsercenterApplication.class, args);
    }

}
