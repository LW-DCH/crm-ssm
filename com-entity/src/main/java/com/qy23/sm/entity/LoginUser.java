package com.qy23.sm.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName LoginUser
 * @Author 刘伟
 * @Date 2020/10/31 19:37
 * @Description
 * @Version 1.0
 **/
@Data
public class LoginUser {
    private String uuid;
    private SysUser sysUser;
    private String os;
    private String ipAddr;
    private String broswerNane;
    private String loginLocation;
    private Long loginTime;
    private Long expireTime;
    private long previousExecuteTime;
    private List<SysMenu> perms;
}
