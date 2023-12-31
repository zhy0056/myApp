export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        path: '/user',
        routes: [
          {
            name: '登录',
            path: '/user/login',
            component: './user/Login',
          },
          {
            name: '注册',
            path: '/user/register',
            component: './user/Register',
          },
        ],
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/welcome',
    name: '欢迎',
    icon: 'smile',
    component: './Welcome',
  },
  {
    path: '/admin',
    name: '管理页',
    icon: 'crown',
    access: 'canAdmin',
    component: './Admin',
    routes: [
      {
        path: '/admin/user-manage',
        name: '用户列表',
        icon: 'smile',
        component: './Admin/UserManage',
      },
      {
        path: '/admin/user-search',
        name: '用户查找及删除',
        icon: 'smile',
        component: './Admin/UserSearch',
      },
      {
        component: './404',
      },
    ],
  }, // {name: '查询表格', icon: 'table', path: '/list', component: './TableList'},
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    name: '个人设置',
    icon: 'smile',
    path: '/accountsettings',
    component: './AccountSettings',
  },
  {
    component: './404',
  },
];
