package com.qy23.sm.useragent;

import com.qy23.sm.spring.SpringUtils;
import com.qy23.sm.useragent.bean.LoginLocationBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName ServiceUtils
 * @Author 刘伟
 * @Date 2020/10/28 15:06
 * @Description
 * @Version 1.0
 **/
public class ServiceUtils {

    /**
     * .
     * 获取IP地址
     *
     * @param
     * @return
     */
    public static String getIpAddr() {
        HttpServletRequest request = getRequest();
        System.out.println("////" + request);
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)) {
            return "127.0.0.1";
        }

        return ip;
    }

    /**
     * .
     * 获取真实地址
     *
     * @param
     * @return
     */
    public static String getloginLocation() {
        String addr = getIpAddr();
        RestTemplate restTemplate = SpringUtils.getBean(RestTemplate.class);
        LoginLocationBean locationBean = restTemplate.getForObject("http://apis.juhe.cn/ip/ipNew?ip=" + addr + "&key=0b0122d5b3055bc2dea21770a8df3f4d", LoginLocationBean.class);

        if ("200".equalsIgnoreCase(locationBean.getResultcode())) {
            Map<String, String> result = locationBean.getResult();
            String country = result.get("Country");
            String province = result.get("Province");
            String city = result.get("City");
            return country + province + city;
        }

        return "未知地区";
    }


    /**
     * 获取requestrequest
     */
    public static HttpServletRequest getRequest() {

        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取Response
     *
     * @return
     */
    public static HttpServletResponse getResponse() {

        return getServletRequestAttributes().getResponse();
    }

    /**
     * 获取RequestContextHolder
     *
     * @return
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 解析UserAgent
     *
     * @param
     * @return
     */
    public static String getUserAgent() {

        return getRequest().getHeader("User-Agent");
    }

    public static void returnJsonStr(String message) {
        HttpServletResponse response = getResponse();
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
