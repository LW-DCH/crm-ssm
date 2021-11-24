package com.qy23.sm.useragent.bean;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName LoginLocationBean
 * @Author 刘伟
 * @Date 2020/10/28 16:29
 * @Description
 * @Version 1.0
 **/
@Data
public class LoginLocationBean {


    /**
     * result : {"Isp":"电信","Country":"中国","City":"黄冈市","Province":"湖北省"}
     * reason : 查询成功
     * error_code : 0
     * resultcode : 200
     */
    private Map<String, String> result;
    private String reason;
    private int error_code;
    private String resultcode;

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public String getResultcode() {
        return resultcode;
    }

}

