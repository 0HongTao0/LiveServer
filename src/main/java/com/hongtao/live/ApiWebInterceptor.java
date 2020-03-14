package com.hongtao.live;

import com.google.gson.Gson;
import com.hongtao.live.module.Response;
import com.hongtao.live.util.JwtUtil;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created 2020/3/13.
 *
 * @author HongTao
 */
public class ApiWebInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokne = request.getHeader("Authorization");
        if (tokne == null) {
            this.setErrorResponse(response, createMsg(Content.Message.MSG_NO_TOKEN));
            return false;
        }
        try {
            request.setAttribute("userId", JwtUtil.parseJwt(tokne).get(JwtUtil.CLAIMS_USER_ID, String.class));
        } catch (Exception e) {
            this.setErrorResponse(response, createMsg(Content.Message.MSG_TOKEN_EXPIRATION));
            return false;
        }
        return true;
    }

    public void setErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);
        response.getWriter().flush();
        response.getWriter().close();
    }

    private String createMsg(String msg) {
        Gson gson = new Gson();
        Response<Object> response = new Response<>(Response.CODE_OFFLINE, msg, new Object());
        return gson.toJson(response);
    }
}
