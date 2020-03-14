package com.hongtao.live.module;

/**
 * Created 2020/3/13.
 *
 * @author HongTao
 */
public class Response<T> {
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_OFFLINE = 0;
    public static final int CODE_FAIL = -1;
    private int code;
    private String msg;
    private T data;

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
