package com.qy23.sm.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.AxiosStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName CrmExceptionHandler
 * @Author 刘伟
 * @Date 2020/10/21 20:25
 * @Description
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtAuthorizationException.class)
    public AxiosResuit myException(JwtAuthorizationException e) {
        return AxiosResuit.success(e.getAxiosStatus());
    }

    @ExceptionHandler(JWTVerificationException.class)
    public AxiosResuit myException(JWTVerificationException e) {
        return AxiosResuit.success(AxiosStatus.TOKEN_VALID_FAILURE);
    }
}
