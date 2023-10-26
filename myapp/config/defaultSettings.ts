import { Settings as LayoutSettings } from '@ant-design/pro-components';

const Settings: LayoutSettings & {
  pwa?: boolean;
  logo?: string;
} = {
  navTheme: 'dark',
  // 拂晓蓝
  primaryColor: '#1890ff',
  layout: 'mix',
  contentWidth: 'Fluid',
  fixedHeader: false,
  fixSiderbar: true,
  colorWeak: false,
  title: '公告系统',
  pwa: false,
  logo: "https://img0.baidu.com/it/u=249424423,2350045638&fm=253&fmt=auto&app=138&f=JPEG?w=366&h=275",

  iconfontUrl: '',
};

export default Settings;
