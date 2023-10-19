package com.zhy.myapp_usercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhy.myapp_usercenter.common.CommonResponse;
import com.zhy.myapp_usercenter.common.ErrorCode;
import com.zhy.myapp_usercenter.common.ResponseUtils;
import com.zhy.myapp_usercenter.exception.MyAppException;
import com.zhy.myapp_usercenter.model.User;
//import com.zhy.myapp_usercenter.model.request.UserDeleteRequest;
import com.zhy.myapp_usercenter.model.request.UserLoginRequest;
import com.zhy.myapp_usercenter.model.request.UserRegisterRequest;
import com.zhy.myapp_usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.zhy.myapp_usercenter.constant.UserConstantValue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 用户功能接口
 */
@RestController
@RequestMapping("/user")
public class UserController {


    //controller要调用service，所以要引入
    @Resource
    private UserService userService;

    //用于校验用户的身份，检验是否为管理员
    private boolean isAdmin(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute(UserConstantValue.LOGIN_STATUS_ON);
        if(user == null || user.getAuthority() != UserConstantValue.ADMIN){
            return false;
        }
        return true;
    }

    public User getSafeUser(User user){
        if(user == null){
            return null;
        }
        User processedUser = new User();
        processedUser.setId(user.getId());
        processedUser.setUsername(user.getUsername());
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



    @PostMapping("/register")
    public CommonResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            return  null;
        }
//        long id = userService.userRegister(userRegisterRequest.getUserAccount(),
//                userRegisterRequest.getUserPassword(), userRegisterRequest.getCheckPassword());
        //最好是先获取变量并校验是否为空.因为controller层校验倾向于对请求参数本身校验，尽量不涉及业务逻辑
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)){
            return null;
        }
        long id = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResponseUtils.success(id);
    }

    @PostMapping("/login")
    public CommonResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest,
                          HttpServletRequest httpServletRequest){
        if(userLoginRequest == null){
            throw new MyAppException(ErrorCode.REQUEST_NULL_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)){
            throw new MyAppException(ErrorCode.REQUEST_VALUE_NULL_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, httpServletRequest);
//        return new CommonResponse<>(0, user, "success", "登录成功");
        return ResponseUtils.success(user);
    }

    @PostMapping("/logout")
    public CommonResponse<Integer> userLogout(HttpServletRequest httpServletRequest){
        if(httpServletRequest == null){
            throw new MyAppException(ErrorCode.REQUEST_NULL_ERROR);
        }
        int result =  userService.userLogout(httpServletRequest);
        return ResponseUtils.success(result);
    }

    //todo 改成CommonResponse之后用户列表页无法识别 但是为什么login功能就能识别呢 注：是因为login之后的页面跳转依赖currentUser
    @GetMapping("/query")
    public List<User> userQuery(String username, HttpServletRequest httpServletRequest){
        if (!isAdmin(httpServletRequest)){
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> result = userService.list(queryWrapper);
        List<User> list = result.stream().map(user -> getSafeUser(user)).collect(Collectors.toList());
        return list;
    }

    @PostMapping("/delete")
    public CommonResponse<Boolean> userDelete(@RequestBody long id, HttpServletRequest httpServletRequest){

        if (!isAdmin(httpServletRequest)){
            return null;
        }

        if(id<=0){
            return null;
        }

        boolean result = userService.removeById(id);
        //boolean result = userService.userDelete(id);
        return ResponseUtils.success(result);
    }

    //todo 改用commonResponse之后无法访问管理员页面，而且前端获取不到后端返回的user，
    // 原因推测为返回类型改变，数据由直接返回改为封在data中
    @GetMapping("/current")
    public User currentUser(HttpServletRequest httpServletRequest){

        User user = (User) httpServletRequest.getSession().getAttribute(UserConstantValue.LOGIN_STATUS_ON);
        if(user == null){
            return null;
        }

        long id = user.getId();
        User currentUser = userService.getById(id);
        User result = getSafeUser(currentUser);
        return result;
    }
}
