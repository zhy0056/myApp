package com.zhy.myapp_usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.myapp_usercenter.common.ErrorCode;
import com.zhy.myapp_usercenter.common.ResponseUtils;
import com.zhy.myapp_usercenter.exception.MyAppException;
import com.zhy.myapp_usercenter.mapper.UserMapper;
import com.zhy.myapp_usercenter.model.User;
import com.zhy.myapp_usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.zhy.myapp_usercenter.constant.UserConstantValue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
* @author zhy
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2023-10-03 19:13:48
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static String salt = "zhang2000hai05yang06";
    final String inValidAccount = "[!@#$%^&*()_]";

    @Resource
    private UserMapper userMapper;

//    @Resource
//    private UserService userService;

//    //用于检验输入是否合法
//    private boolean isValid(String userAccount, String userPassword){
//        //检查用户名长度
//        if (userAccount.length() < 4){
//            //System.out.println("用户名长度不可小于4位！");
//            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "学号长度不可小于4位");
//        }
//
//        //检查密码长度
//        if (userPassword.length() < 8 ){
//            //System.out.println("密码长度不可小于8位!");
//            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "密码长度不可小于4位");
//        }
//
//        return true;
//
//    }


    public User getSafeUser(User user){
        if(user == null){
            return null;
        }
        User processedUser = new User();
        processedUser.setId(user.getId());
        processedUser.setName(user.getName());
        processedUser.setUserAccount(user.getUserAccount());
        processedUser.setAvatar(user.getAvatar());
        processedUser.setGender(user.getGender());
        processedUser.setPhone(user.getPhone());
        processedUser.setEmail(user.getEmail());
        processedUser.setUserStatus(user.getUserStatus());
        processedUser.setCreateTime(user.getCreateTime());
        processedUser.setAuthority(user.getAuthority());
        return processedUser;
    }


    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword, String name) {


        /**
         * 1.校验学号和密码
         */
        //检查用户提交的学号、密码和密码确认是否为空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, name)) {
            //System.out.println("用户名或密码不能为空!");
            throw new MyAppException(ErrorCode.REQUEST_VALUE_NULL_ERROR, "学号、姓名或密码不可为空！");
        }

        //检查学号长度
        if (userAccount.length() < 6){
            //System.out.println("用户名长度不可小于4位！");
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "学号长度不可小于6位！");
        }

        //检查密码长度
        if (userPassword.length() < 8 ){
            //System.out.println("密码长度不可小于8位!");
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "密码长度不可小于8位！");
        }


        //检查用户名是否包含特殊字符
        Pattern pattern = Pattern.compile(inValidAccount);
        Matcher matcher = pattern.matcher(userAccount);
        boolean found = matcher.find();
        if(found){
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "学号不能包含特殊字符！");
        }

        //两次密码输入是否相同
        if (!userPassword.equals(checkPassword)){
            //System.out.println("两次密码输入不同!");
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "两次密码输入不同!");
        }

        //检查用户名是否重复，由于需要查询数据库放在最后
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            //System.out.println("该用户名已被注册!");
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "该学号已被注册！");
        }

        /**
         * 2.对密码进行加密
         */
        //使用md5对用户密码进行加密
        String encodedPassword = DigestUtils.md5DigestAsHex(
                (salt + salt + userPassword + salt + salt).getBytes());
        // System.out.println("原密码：" + rawPassword);
        // System.out.println("加密后的密码：" + encodedPassword);
        //向用户数据库插入密码
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encodedPassword);
        user.setName(name);
        user.setAvatar("https://img2.baidu.com/it/u=3660282162,1838273748&fm=253&fmt=auto&app=138&f=PNG?w=275&h=275");
        //需要判断是否分配id，不然返回类型对应不上
        boolean judge = this.save(user);
        if (!judge){
            throw new MyAppException(ErrorCode.SYSTEM_ERROR, "系统错误!");
        }
        //System.out.println("恭喜您注册成功！您的用户id为："+user.getId());
        return user.getId();
    }

    @Override
    public int userLogout(HttpServletRequest request) {

        request.getSession().removeAttribute(UserConstantValue.LOGIN_STATUS_ON);
        return 1;
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {

        //首先校验用户名、密码是否为空
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            //System.out.println("用户名或密码不能为空!");
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "学号长度不可小于4位！");
        }

        //检查用户名长度
        if (userAccount.length() < 4){
            //System.out.println("用户名长度不可小于4位！");
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "学号长度不可小于4位！");
        }

        //检查密码长度
        if (userPassword.length() < 8 ){
            //System.out.println("密码长度不可小于8位!");
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "密码长度不可小于8位！");
        }


        //检查用户名是否包含特殊字符
        Pattern pattern = Pattern.compile(inValidAccount);
        Matcher matcher = pattern.matcher(userAccount);
        boolean found = matcher.find();
        if(found){
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "学号不能包含特殊字符！");
        }

        //转换成md5再进行比对
        String encodedPassword = DigestUtils.md5DigestAsHex(
                (salt + salt + userPassword + salt + salt).getBytes());
        // System.out.println("原密码：" + rawPassword);
        // System.out.println("加密后的密码：" + encodedPassword);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encodedPassword);

        User user = userMapper.selectOne(queryWrapper);

        //用户名或密码错误
        if(user == null){
            log.info("用户登录失败！");
            //System.out.println("用户登录失败！");
            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "学号或密码错误！");
        }
        //脱敏
        User processedUser = getSafeUser(user);

        //记录用户的登录态session
        request.getSession().setAttribute(UserConstantValue.LOGIN_STATUS_ON, processedUser);

        return processedUser;
    }

//    @Override
//    public List<User> userQuery(String username) {
//
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        if(StringUtils.isNotBlank(username)){
//            queryWrapper.like("username", username);
//        }
//        queryWrapper.select("id", "username", "userAccount", "avatar", "gender", "phone", "email",
//                "userStatus", "createTime", "updateTime", "authority", "isDelete");
//        return userService.list(queryWrapper);
//    }

//    @Override
//    public boolean userDelete(long id) {
//
//        if(id <= 0){
//            throw new MyAppException(ErrorCode.REQUEST_VALUE_ERROR, "用户id不存在！");
//        }
//        return userService.removeById(id);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", id);
//
//        User user = userMapper.selectOne(queryWrapper);
//
//        if(user == null){
//            log.info("用户不存在！");
//            return false;
//        }
//
//        user.setIsDelete(1);
//
//        return true;
  //  }


}




