package com.qy23.sm.http;

import java.util.HashMap;

/**
 * @ClassName AxiosResuit
 * @Author 刘伟
 * @Date 2020/10/17 21:24
 * @Description
 * @Version 1.0
 **/
public class AxiosResuit extends HashMap<String, Object> {

    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";

    public AxiosResuit() {
    }

    public AxiosResuit(AxiosStatus axiosStatus) {
        super.put(STATUS, axiosStatus.getStatus());
        super.put(MESSAGE, axiosStatus.getMessage());
    }

    public static AxiosResuit success() {
        return new AxiosResuit(AxiosStatus.OK);
    }

    public static AxiosResuit success(AxiosStatus axiosStatus) {
        return new AxiosResuit(axiosStatus);
    }

    public static AxiosResuit success(Object data) {
        AxiosResuit axiosResuit = new AxiosResuit(AxiosStatus.OK);
        axiosResuit.put(DATA, data);
        return axiosResuit;
    }

    public static AxiosResuit error() {
        return new AxiosResuit(AxiosStatus.ERROR);
    }

    public static AxiosResuit error(AxiosStatus axiosStatus) {
        return new AxiosResuit(axiosStatus);
    }

    public static AxiosResuit error(AxiosStatus axiosStatus, Object obj) {
        AxiosResuit axiosResuit = new AxiosResuit(axiosStatus);
        axiosResuit.put(DATA, obj);
        return axiosResuit;
    }

    public static AxiosResuit error(Object data) {
        AxiosResuit axiosResuit = new AxiosResuit(AxiosStatus.ERROR);
        axiosResuit.put(DATA, data);
        return axiosResuit;
    }
}
