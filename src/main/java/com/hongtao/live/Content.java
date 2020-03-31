package com.hongtao.live;

/**
 * Created 2020/3/14.
 *
 * @author HongTao
 */
public interface Content {
    String IP = "192.168.0.106";

    class Message {
        public static final String MSG_NO_TOKEN = "未携带token";
        public static final String MSG_TOKEN_EXPIRATION = "token过期";

        public static final String MSG_LOGIN_SUCCESS = "登录成功";
        public static final String MSG_LOGIN_FAIL = "登录失败，请检查账号密码。";
        public static final String MSG_REGISTERED_SUCCESS = "注册成功,请登录。";
        public static final String MSG_REGISTERED_FAIL_SAME_ID = "ID已存在";

        public static final String MSG_ME_GET_SUCCESS = "获取用户信息成功";
        public static final String MSG_ME_ALTER_SUCCESS = "修改成功";

        public static final String MSG_ROOM_GET_SUCCESS = "直播间存在,获取信息成功";
        public static final String MSG_ROOM_GET_FAIL = "直播间不存在,需要创建直播间";
        public static final String MSG_ROOM_CREATE_SUCCESS = "创建直播间成功";
        public static final String MSG_ROOM_UPDATE_SUCCESS = "更新直播间成功";

        public static final String MSG_MESSAGE_SEND_SUCCESS = "发送信息成功";
        public static final String MSG_MESSAGE_GET_SUCCESS = "获取信息成功";

        public static final String MSG_ATTENTION_ROOM_SUCCESS = "关注直播间成功";
        public static final String MSG_GET_OFF_ATTENTION_ROOM_SUCCESS = "取消关注成功";
        public static final String MSG_GET_ATTENTION_ROOM_SUCCESS = "获取关注成功";

        public static final String MSG_GIFT_GET_SUCCESS = "获取礼物列表成功";
        public static final String MSG_GIFT_SEND_SUCCESS = "送礼物成功";
        public static final String MSG_GIFT_SEND_FAIL = "送礼物失败，余额不足";

        public static final String MSG_MONEY_RECHARGE_SUCCESS = "充值成功";
        public static final String MSG_MONEY_WITHDRAW_SUCCESS = "提现成功";
        public static final String MSG_MONEY_WITHDRAW_FAIL = "提现失败，余额不足";
        public static final String MSG_MONEY_GET_RECORD_SUCCESS = "获取余额记录成功";

        public static final String MSG_ADDRESS_GET_SUCCESS = "获取地址成功";
    }

    class Code {
        public static final int CODE_LOGIN_SUCCESS = 1;
        public static final int CODE_LOGIN_FAIL = -1;
        public static final int CODE_REGISTERED_SUCCESS = 1;
        public static final int CODE_REGISTERED_FAIL_SAME_ID = -1;

        public static final int CODE_ROOM_EXIST = 1;
        public static final int CODE_ROOM_NOT_EXIST = -1;
        public static final int CODE_ROOM_CREATE = 2;
        public static final int CODE_ROOM_UPDATE = 3;
    }
}
