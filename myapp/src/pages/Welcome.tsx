import React from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Alert, Typography } from 'antd';
import styles from './Welcome.less';

const CodePreview: React.FC = ({ children }) => (
    <pre className={styles.pre}>
    <code>
      <Typography.Text copyable>{children}</Typography.Text>
    </code>
  </pre>
);

const Welcome: React.FC = () => {
    return (
        <PageContainer>
            <Card>
                <Alert
                    message={
                      <div>
                        implemented:
                        <br />
                        公告系统的用户注册、登录、注销以及管理员查看用户列表、根据用户名或账号查找用户已实现。鉴权、全局请求响应拦截器、通用返回对象、自定义异常及错误码、全局异常处理器等已完成。
                      </div>
                    }
                    type="success"
                    // showIcon
                    //banner
                    style={{
                        margin: -12,
                        marginBottom: 24,
                    }}
                />
              <Alert
                message={
                  <div>
                    todo:
                    <br />
                    1.实现剩余管理员功能：管理员删除用户；管理公告（发布、删除、查看、搜索）；编辑个人信息；
                    <br />
                    2.实现剩余用户功能：查看、搜索（标题）公告；编辑个人信息；
                    <br />
                    3.实现注册时的邮箱验证以及找回密码功能；
                    <br />
                    4.用双检锁单例模式管理JSON格式化处理的对象；
                    <br />
                    5.完善单元测试；
                    <br />
                    6.部署及上线；
                  </div>
                }
                type="warning"
                // showIcon
                // banner
                style={{
                  margin: -12,
                  marginBottom: 24,
                }}
              />
              <Alert
                message={
                  <div>
                    problems(error):
                    <br />
                    暂无。
                  </div>
                }
                type="error"
                // showIcon
                // banner
                style={{
                  margin: -12,
                  marginBottom: 24,
                }}
              />
                <Typography.Text strong>
                    本项目github地址{' '}
                    <a
                        href="https://github.com/zhy0056/myApp"
                        rel="noopener noreferrer"
                        target="__blank"
                    >
                        欢迎访问
                    </a>
                </Typography.Text>
                <CodePreview>https://github.com/zhy0056/myApp</CodePreview>
                <Typography.Text
                    strong
                    style={{
                        marginBottom: 12,
                    }}
                >
                    高级布局{' '}
                    <a
                        href="https://procomponents.ant.design/components/layout"
                        rel="noopener noreferrer"
                        target="__blank"
                    >
                        欢迎使用
                    </a>
                </Typography.Text>
                <CodePreview>yarn add @ant-design/pro-layout</CodePreview>
            </Card>
        </PageContainer>
    );
};

export default Welcome;
