import React from 'react';
import { UploadOutlined } from '@ant-design/icons';
import { Button, Upload, message } from 'antd';
import ProForm, {
  ProFormSelect,
  ProFormText,

} from '@ant-design/pro-form';
import { useRequest } from 'umi';


import styles from './BaseView.less';
import {currentUser, updateLoginUser} from "@/services/ant-design-pro/api";

//头像组件 方便以后独立，增加裁剪之类的功能
const AvatarView = ({ avatar }: { avatar: string }) => (
  <>
    <div className={styles.avatar_title}>头像</div>
    <div className={styles.avatar}>
      <img src={avatar} alt="avatar" />
    </div>
    <Upload showUploadList={false}>
      <div className={styles.button_view}>
        <Button>
          <UploadOutlined />
          更换头像
        </Button>
      </div>
    </Upload>
  </>
);

const BaseView: React.FC = () => {
  const {  data:loginUser, loading } = useRequest(() => {
    return currentUser();
  });

  const getAvatarURL = () => {
    if (loginUser) {
      if (loginUser.avatar) {
        return loginUser.avatar;
      }
      return '用户无头像'
    }
    return '未检测到currentUser';
  };

  const handleFinish = async (fields: API.CurrentUser) => {
    const hide = message.loading('正在修改');
    try {
      await updateLoginUser({
        //id: fields.id ?? 0,
        ...fields,
      });
      hide();
      message.success('修改成功');

      return true;
    } catch (error) {
      hide();
      message.error('修改失败请重试！');
      return false;
    }
  };

  return (
    <div className={styles.baseView}>
      {loading ? null : (
        <>
          <div className={styles.left}>
            <ProForm
              layout="vertical"
              // onFinish={handleFinish}
              onFinish={async (values) => {
                await handleFinish(values as API.CurrentUser);
              }}

              submitter={{
                resetButtonProps: {
                  style: {
                    display: 'none',
                  },
                },
                submitButtonProps: {
                  children: '更新基本信息',
                },
              }}
              initialValues={{
                ...loginUser,
              }}
              hideRequiredMark
            >
              <ProFormText
                width="md"
                name="name"
                label="姓名"
                rules={[
                  {
                    required: false,
                    message: '请输入您的姓名!',
                  },
                ]}
              />
              <ProFormText
                width="md"
                name="userPassword"
                label="密码"
                rules={[
                  {
                    required: false,
                    message: '请输入您的密码!',
                  },
                ]}
              />
              <ProFormSelect
                width="sm"
                name="gender"
                label="性别"
                rules={[
                  {
                    required: false,
                    message: '请选择您的性别!',
                  },
                ]}
                options={[
                  {
                    label: '男',
                    value: '1',
                  },{
                    label: '女',
                    value: '0',
                  },
                ]}
              />
              <ProFormText
                width="md"
                name="phone"
                label="电话号码"
                rules={[
                  {
                    required: false,
                    message: '请输入您的电话号码!',
                  },
                ]}
              />
              <ProFormText
                width="md"
                name="email"
                label="邮箱"
                rules={[
                  {
                    required: false,
                    message: '请输入您的邮箱!',
                  },
                ]}
              />
              <div style={{ display: 'none' }}>
                <ProFormSelect
                  width="sm"
                  name="authority"
                  label="用户身份"
                  initialValue={loginUser?.authority}
                />
              </div>
            </ProForm>
          </div>
          <div className={styles.right}>
            <AvatarView avatar={getAvatarURL()} />
            {/*{console.log(getAvatarURL())}*/}
          </div>
        </>
      )}
    </div>
  );
};

export default BaseView;
