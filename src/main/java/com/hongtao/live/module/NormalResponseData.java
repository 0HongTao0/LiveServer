package com.hongtao.live.module;

/**
 * Created 2020/3/25.
 *
 * @author HongTao
 */
public class NormalResponseData {
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAIL = -1;

    private int code;

    public NormalResponseData(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
