package com.qy23.sm.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.qy23.sm.login.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Author 刘伟
 * @Date 2020/10/27 16:26
 * @Description 登录拦截
 * @Version 1.0
 **/
public class JwtAuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean b = tokenService.tokenAuthorization(request);
        if (!b) {
            throw new JWTVerificationException("token认证不正确");
        }
        return b;
    }
}
