package com.qy23.sm.annatation;

import com.qy23.sm.cache.JSONUtils;
import com.qy23.sm.entity.LoginUser;
import com.qy23.sm.entity.SysMenu;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.AxiosStatus;
import com.qy23.sm.login.TokenService;
import com.qy23.sm.useragent.ServiceUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName PermAspect
 * @Author 刘伟
 * @Date 2020/11/1 16:56
 * @Description
 * @Version 1.0
 **/
@Component
@Aspect
public class PermAspect {

    @Autowired
    private TokenService tokenService;
    @Autowired
    public JSONUtils jsonUtils;

    @Pointcut("@annotation(com.qy23.sm.annatation.HasPerm)")
    public void myPoinCut() {
    }

    @Before(value = "myPoinCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HasPerm declaredAnnotation = method.getDeclaredAnnotation(HasPerm.class);
        if (declaredAnnotation != null) {
            String perm = declaredAnnotation.perm();
            LoginUser loginUser = tokenService.getLoginUser(ServiceUtils.getRequest());
            List<SysMenu> perms = loginUser.getPerms();
            boolean b = perms.stream().anyMatch(sysMenu -> sysMenu.getPerms().equalsIgnoreCase(perm));
            if (!b) {
                ServiceUtils.returnJsonStr(jsonUtils.obj2str(AxiosResuit.error(AxiosStatus.TOKEN_VALID_FAILURE)));
            }
        }
    }
}
