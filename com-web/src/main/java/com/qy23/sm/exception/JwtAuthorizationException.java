package com.qy23.sm.exception;

import com.qy23.sm.http.AxiosStatus;

/**
 * @ClassName JwtAuthorizationException
 * @Author 刘伟
 * @Date 2020/10/27 22:45
 * @Description
 * @Version 1.0
 **/
public class JwtAuthorizationException extends RuntimeException {

    private AxiosStatus axiosStatus;

    public AxiosStatus getAxiosStatus() {
        return axiosStatus;
    }

    public void setAxiosStatus(AxiosStatus axiosStatus) {
        this.axiosStatus = axiosStatus;
    }

    public JwtAuthorizationException(AxiosStatus axiosStatus) {
        this.axiosStatus = axiosStatus;
    }
}
