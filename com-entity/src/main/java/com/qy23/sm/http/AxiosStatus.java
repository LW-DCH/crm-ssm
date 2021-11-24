package com.qy23.sm.http;

public enum AxiosStatus {

    OK(200, "操作成功"),
    ERROR(500, "操作失败"),
    EXT_ERROR(300, "上传格式不支持"),
    FILE_TOLONG(301, "文件太大"),
    NOT_IMAGE(302, "不是图片"),
    VALID_IFAILURE(303, "表单验证失败"),
    TOKEN_NOT(405, "token不正确"),
    USERNAME_NOT_EMPTY(401, "用户名不能为空"),
    USERNAMEORPASSWORD_NOT_SURE(402, "用户名或密码不正确"),
    PASSWORD_NOT_EMPTY(403, "密码不能为空"),
    TOKEN_VALID_FAILURE(444, "未认证"),
    ;
    private int status;
    private String message;

    AxiosStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    AxiosStatus() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
