package com.hongtao.live.module;

/**
 * Created 2020/3/14.
 *
 * @author HongTao
 */
public class LoginData {
    private String token;

    public LoginData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
