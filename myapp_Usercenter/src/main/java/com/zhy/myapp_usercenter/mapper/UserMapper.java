package com.zhy.myapp_usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.myapp_usercenter.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ThinkPad
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2023-10-03 19:13:48
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




