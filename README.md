# myApp

## I. 项目概况

**Implemented:**

> 管理系统的用户注册、登录、注销、编辑个人信息以及管理员查看用户列表、搜索用户已实现。鉴权、全局请求响应拦截器、通用返回对象、自定义异常及错误码、全局异常处理器等已完成。 

**Todo:**

> 1.实现剩余管理员功能：管理员删除用户；
> 2.实现注册时的邮箱验证以及找回密码功能；实现上传头像
> 
>3.用双检锁单例模式管理JSON格式化处理的对象；
> 4.部署及上线；  
> 
> **拓展思路：**
>
> Redis存储用户登录分布式session；
>
> 管理员批量导入用户；
>
> 用户上传头像压缩校验；
>
> Elasticsearch搜索用户信息；
>
> RBAC实现权限管理；
>
> 单点登录；
>
> AOP/Filter实现全局请求拦截，统一判断权限及记录日志；

**Error:**

> 暂无

## II. 项目介绍、展示

 本项目技术栈包括React、Spring Boot等，是一个**前后端分离**项目。 

⭐主要功能：

- 用户登录、注册、退出登录
- 编辑个人信息
- 用户管理（管理员）
  - 查看用户列表
  - 按学号、姓名搜索用户
  - 删除用户

扩展方向：

- 大学生竞赛管理系统（与另一个仓库CMSys结合）
- 学生考勤系统

![Image text]([https://github.com/your_github/address/blob/master/image/1.png](https://github.com/zhy0056/myApp/blob/main/images/1.png))

![Image text](https://github.com/your_github/address/blob/master/image/1.png)

![Image text](https://github.com/your_github/address/blob/master/image/1.png)

![Image text](https://github.com/your_github/address/blob/master/image/1.png)

![Image text](https://github.com/your_github/address/blob/master/image/1.png)

![Image text](https://github.com/your_github/address/blob/master/image/1.png)

## III. 技术选型

- 前端
  1. HTML、CSS、JavaScript；
  2. Ant Design Pro 项目模板 & ProComponents组件库；
  3. React开发框架；
  4. Umi框架与全局请求响应拦截器；
  5.  TypeScript；
  6. 正向/反向代理；

- 后端
  1. Java编程语言；Spring+SpringMVC+SpringBoot框架；
  2. MyBatis+MyBatis Plus框架+MyBatis-X工具；
  3. MySQL数据库；
  4. jUnit单元测试；
  5.  通用返回对象、自定义异常及错误码、全局异常处理器； 

## III. 后端目录结构

- 后端

```java
├─java
│  └─com
│      └─zhy
│          └─myapp_usercenter
│              │  MyappUsercenterApplication.java//启动类
│              │
│              ├─common
│              │      CommonResponse.java//通用返回模板
│              │      ErrorCode.java//自定义错误码
│              │      ResponseUtils.java//处理响应
│              │
│              ├─constant
│              │      UserConstantValue.java//一些常量（登录态、权限）
│              │
│              ├─controller
│              │      UserController.java//控制类
│              │
│              ├─exception//自定义异常类封装
│              │      GlobalExceptionHandler.java//全局异常处理器
│              │      MyAppException.java//封装RuntimeException
│              │
│              ├─mapper//数据访问
│              │      UserMapper.java
│              │
│              ├─model//对象类
│              │  │  User.java
│              │  │
│              │  └─request//自定义请求对象
│              │          UserDeleteRequest.java
│              │          UserLoginRequest.java
│              │          UserRegisterRequest.java
│              │
│              ├─service
│              │  │  UserService.java//业务逻辑接口
│              │  │
│              │  └─impl
│              │          UserServiceImpl.java//业务逻辑实现类
│              │
│              └─utils
└─resources
    │  application.yml//SpringBoot全局配置文件
    │
    └─mapper
            UserMapper.xml

```

## IV. 后端业务逻辑

### 用户注册

1、用户在前端输入学号、姓名、密码、二次密码确认

2、后端根据学号、姓名、密码、二次密码确认进行校验

> 非空

> 学号不小于6位

> 密码长度不小于8位

> 校验特殊字符

> 校验学号是否已存在

> 密码和二次密码是否相同

3、对密码进行加密（md5）

4、将数据保存到数据库中

5、脱敏后返回用户user

### 用户登录

1、用户在前端输入学号、密码

2、后端根据学号、密码进行校验

> 非空

> 学号不小于6位

> 密码长度不小于8位

> 校验特殊字符

3、对密码进行加密（md5），与数据库中信息进行比对

4、记录用户登录态

5、脱敏后返回用户user

### 用户注销（退出登录）

移除登录态

### 用户编辑个人信息

1、用户在前端输入更新后的信息（字段非必须）

2、后端校验当前用户

> 非空
>
> id合法且存在

3、更新信息其中密码需要加密后存储

4、返回布尔类型，表示是否更新成功

### 查看用户列表/搜索用户

1、校验身份，仅管理员有该权限

2、返回脱敏后列表

