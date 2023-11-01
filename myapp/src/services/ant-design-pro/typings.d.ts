// @ts-ignore
/* eslint-disable */

declare namespace API {
    type CurrentUser = {
        id: number;
        name: string;
        userAccount: string;
        avatar?: string;
        gender: number;
        phone: string
        email?: string;
        userStatus: number;
        createTime: Date;
        authority: number;
    };

    type LoginResult = {
        status?: string;
        type?: string;
        currentAuthority?: string;
    };


    type RegisterResult = number;

    type UpdateResult = boolean;

    type DeleteResult = boolean;

    type PageParams = {
        current?: number;
        pageSize?: number;
    };

    type RuleListItem = {
        key?: number;
        disabled?: boolean;
        href?: string;
        avatar?: string;
        name?: string;
        owner?: string;
        desc?: string;
        callNo?: number;
        status?: number;
        updatedAt?: string;
        createdAt?: string;
        progress?: number;
    };

    type RuleList = {
        data?: RuleListItem[];
        /** 列表的内容总数 */
        total?: number;
        success?: boolean;
    };

    type FakeCaptcha = {
        code?: number;
        status?: string;
    };

    type LoginParams = {
        userAccount?: string;
        userPassword?: string;
        autoLogin?: boolean;
        type?: string;
    };

    type DeleteParams = {
      userAccount?: string;
      type?: string;
    };

    type RegisterParams = {
        userAccount?: string;
        userPassword?: string;
        checkPassword?: string;
        name?: string;
        type?: string;
    };

    type BaseResponse<T> = {
      code: number,
      data: T,
      message: string,
      description: string,
    }

    type UserUpdateRequest = {
      id: number;
      name?: string;
      userPassword?: string;
      avatar?: string;
      gender?: number;
      phone?: string;
      authority: number;

    }

    type QueryParams = {
        name?: string;
        type?: string;
    };

    type ErrorResponse = {
        /** 业务约定的错误码 */
        errorCode: string;
        /** 业务上的错误信息 */
        errorMessage?: string;
        /** 业务上的请求是否成功 */
        success?: boolean;
    };

    type NoticeIconList = {
        data?: NoticeIconItem[];
        /** 列表的内容总数 */
        total?: number;
        success?: boolean;
    };

    type NoticeIconItemType = 'notification' | 'message' | 'event';

    type NoticeIconItem = {
        id?: string;
        extra?: string;
        key?: string;
        read?: boolean;
        avatar?: string;
        title?: string;
        status?: string;
        datetime?: string;
        description?: string;
        type?: NoticeIconItemType;
    };

    type CommonResponse<T> = {
      code: number;
      data: T;
      msg: string;
      description: string;
    };

}
