package com.qy23.sm.async;

import com.qy23.sm.email.EmailService;
import com.qy23.sm.entity.SysLoginLog;
import com.qy23.sm.service.ISysLoginLogService;
import com.qy23.sm.spring.SpringUtils;
import com.qy23.sm.useragent.ServiceUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;

/**
 * @ClassName AsyncFactory
 * @Author 刘伟
 * @Date 2020/10/22 21:37
 * @Description 异步任务工厂
 * @Version 1.0
 **/
public class AsyncFactory {

    /**
     * 异步邮件
     *
     * @param to
     * @param message
     * @return
     */
    public static Runnable executeEmail(String to, String message) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SpringUtils.getBean(EmailService.class).sendMail(to, message);
            }
        };
        return runnable;
    }

    /**
     * 异步日志
     */
    public static Runnable executeLoginLog(String status, String msg, String username) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServiceUtils.getUserAgent());
        ISysLoginLogService logService = SpringUtils.getBean(ISysLoginLogService.class);
        String ipAddr = ServiceUtils.getIpAddr();
        String location = ServiceUtils.getloginLocation();
        Runnable runnable = () -> {
            SysLoginLog sysLoginLog = new SysLoginLog();
            sysLoginLog.setLoginTime(LocalDateTime.now());
            sysLoginLog.setOs(userAgent.getOperatingSystem().getName());
            sysLoginLog.setBrowser(userAgent.getBrowser().getName());
            sysLoginLog.setIpaddr(ipAddr);
            sysLoginLog.setLoginLocation(location);
            sysLoginLog.setMsg(msg);
            sysLoginLog.setStatus(status);
            sysLoginLog.setUserName(username);
            logService.add(sysLoginLog);
        };
        return runnable;
    }
}
