package com.hongtao.live;

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
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            this.setErrorResponse(response, "未携带token");
            return false;
        }
        String token = authorization;
        try {
            request.setAttribute("user", JwtUtil.parseJwt(token));
        } catch (Exception e) {
            this.setErrorResponse(response, e.getMessage());
            return false;
        }
        return true;
    }

    protected void setErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);
        response.getWriter().flush();
        response.getWriter().close();

    }
}
