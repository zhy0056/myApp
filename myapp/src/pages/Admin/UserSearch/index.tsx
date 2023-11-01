import { useRef } from 'react';
import type { ProColumns, ActionType } from '@ant-design/pro-table';
import { ProTable, TableDropdown } from '@ant-design/pro-components';
import {userSearch} from "@/services/ant-design-pro/api";
import {Image} from "antd";
const columns: ProColumns<API.CurrentUser>[] = [
  {
    dataIndex: 'id',
    valueType: 'indexBorder',
    width: 48,
    search: false,

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
    search: false,

    render: (_, record) => (
      <div>
        <Image src={record.avatar} width={60} />
      </div>
    ),
  },
  {
    title: '性别',
    dataIndex: 'gender',
    search: false,
    valueType: 'select',
    valueEnum: {
      0: { text: '女' },
      1: {text: '男'},
      2: {text: '未设定'},
    },
  },
  {
    title: '电话',
    dataIndex: 'phone',
    copyable: true,
    search: false,
  },
  {
    title: '邮件',
    dataIndex: 'email',
    copyable: true,
    search: false,
  },
  {
    title: '状态',
    dataIndex: 'userStatus',
    search: false,
  },
  {
    title: '身份',
    dataIndex: 'authority',
    valueType: 'select',
    search: false,
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
    search: false,
  },
  {
    title: '操作',
    valueType: 'option',
    key: 'option',
    render: (text, record, _, action) => [
      <TableDropdown
        key="actionGroup"
        // onSelect={async (menus: string,values: API.DeleteParams) => {
        //   //const handleSubmit = async (values: API.LoginParams) => {
        //     if (menus === 'delete') {
        //     const isDelete = await deleteUser({...values});
        //     if(isDelete){
        //       const defaultDeleteSuccessMessage = '删除成功！';
        //       message.success(defaultDeleteSuccessMessage);
        //     }
        //   }
        //   action?.reload();
        // }}
        menus={[
          { key: 'delete', name: '删除' },
        ]}
      />,
    ],
  },
  // {
  //   title: '操作',
  //   valueType: 'option',
  //   render: (text, record, _, action) => [
  //     <a
  //       key="editable"
  //       onClick={() => {
  //         action?.startEditable?.(record.id);
  //       }}
  //     >
  //       删除用户
  //     </a>,
  //   ],
  // },
];

export default () => {
  const actionRef = useRef<ActionType>();
  return (
    <ProTable<API.CurrentUser>
      columns={columns}
      actionRef={actionRef}
      cardBordered
      request={async (params = {}, sort, filter) => {

        console.log(sort, filter);
        console.log(params);
        const userList = await userSearch(params);
        return {
          data: userList
        }
      }}
      editable={{
        type: 'multiple',
      }}
      columnsState={{
        persistenceKey: 'pro-table-singe-demos',
        persistenceType: 'localStorage',
      }}
      rowKey="id"
      search={{
        labelWidth: 'auto',
      }}
      form={{
        // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
        syncToUrl: (values, type) => {
          if (type === 'get') {
            return {
              ...values,
              created_at: [values.startTime, values.endTime],
            };
          }
          return values;
        },
      }}
      pagination={{
        pageSize: 5,
      }}
      dateFormatter="string"
      headerTitle="高级表格"
    />
  );
};
