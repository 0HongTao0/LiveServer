package com.hongtao.live;

/**
 * Created 2020/3/14.
 *
 * @author HongTao
 */
public interface Content {

    class Message{
        public static final String MSG_NO_TOKEN = "未携带token";
        public static final String MSG_TOKEN_EXPIRATION = "token过期";

        public static final String MSG_LOGIN_SUCCESS = "登录成功";
        public static final String MSG_LOGIN_FAIL = "登录失败，请检查账号密码。";
        public static final String MSG_REGISTERED_SUCCESS = "注册成功,请登录。";
        public static final String MSG_REGISTERED_FAIL_SAME_ID = "ID已存在";
    }

    class Code{
        public static final int CODE_LOGIN_SUCCESS = 1;
        public static final int CODE_LOGIN_FAIL = -1;
        public static final int CODE_REGISTERED_SUCCESS = 1;
        public static final int CODE_REGISTERED_FAIL_SAME_ID = -1;
    }
}
