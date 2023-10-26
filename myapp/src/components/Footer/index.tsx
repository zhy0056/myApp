import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
const Footer: React.FC = () => {
  const defaultMessage = 'by zhy';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'github',
          title: <><GithubOutlined/> zhy myApp GitHub</>,
          href: 'https://github.com/zhy0056/myApp/',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
