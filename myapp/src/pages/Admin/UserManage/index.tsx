import type { ProColumns } from '@ant-design/pro-components';
import { ProTable } from '@ant-design/pro-components';
import {Image} from 'antd';
import {userQuery} from "@/services/ant-design-pro/api";

// const valueEnum = {
//   0: 'close',
//   1: 'running',
//   2: 'online',
//   3: 'error',
// };

// export type TableListItem = {
//   key: number;
//   name: string;
//   containers: number;
//   creator: string;
//   status: string;
//   createdAt: number;
//   memo: string;
// };

//const tableListDataSource: TableListItem[] = [];
const tableListDataSource: API.CurrentUser[] = await userQuery();

//const creators = ['付小小', '曲丽丽', '林东东', '陈帅帅', '兼某某'];

// for (let i = 0; i < 5; i += 1) {
//   tableListDataSource.push({
//     id: 1,
//     username: 'AppName',
//     userAccount: 'string',
//     avatar: 'string',
//     gender: 1,
//     phone: 'string',
//     email: 'string',
//     userStatus: 0,
//     createTime: Date,
//     authority: 1,
//     creator: creators[Math.floor(Math.random() * creators.length)],
//     status: valueEnum[Math.floor(Math.random() * 10) % 4],
//     createdAt: Date.now() - Math.floor(Math.random() * 100000),
//     memo:
//       i % 2 === 1
//         ? '很长很长很长很长很长很长很长的文字要展示但是要留下尾巴'
//         : '简短备注文案',
//   });
// }


const columns: ProColumns<API.CurrentUser>[] = [
  {
    dataIndex: 'id',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: '姓名',
    dataIndex: 'name',
    copyable: true,
  },
  {
    title: '学号',
    dataIndex: 'userAccount',
    copyable: true,
  },
  {
    title: '头像',
    dataIndex: 'avatar',
    render: (_, record) => (
      <div>
        <Image src={record.avatar} width={60} />
      </div>
    ),
  },
  {
    title: '性别',
    dataIndex: 'gender',
    valueType: 'select',
    valueEnum: {
      0: { text: '女' },
      1: {text: '男',},
    },
  },
  {
    title: '电话',
    dataIndex: 'phone',
    copyable: true,
  },
  {
    title: '邮件',
    dataIndex: 'email',
    copyable: true,
  },
  {
    title: '状态',
    dataIndex: 'userStatus',
  },
  {
    title: '身份',
    dataIndex: 'authority',
    valueType: 'select',
    valueEnum: {
      0: { text: '学生', status: 'Success' },
      1: {
        text: '管理员',
        status: 'Error',
      },
    },
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    valueType: 'dateTime',
  },
];

export default () => {
  return (
    <ProTable<API.CurrentUser>
      dataSource={tableListDataSource}
      rowKey="key"
      pagination={{
        pageSize: 5,
      }}
      columns={columns}
      search={false}
      dateFormatter="string"
      headerTitle="学生列表"
      // toolBarRender={() => [
      // ]}
    />
  );
};
