package com.qy23.sm.login;

import com.qy23.sm.entity.LoginUser;
import com.qy23.sm.entity.SysMenu;
import com.qy23.sm.entity.SysUser;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.useragent.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName LoginConller
 * @Author 刘伟
 * @Date 2020/10/25 21:36
 * @Description
 * @Version 1.0
 **/
@RestController
public class AController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    @GetMapping("login11")
    public AxiosResuit login(String userName, String password) {

        return loginService.doLogin(userName, password);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("aboutUser")
    public AxiosResuit getUserRouter() {
        LoginUser loginUser = tokenService.getLoginUser(ServiceUtils.getRequest());
        SysUser sysUser = loginUser.getSysUser();
        Long userId = sysUser.getUserId();
        List<SysMenu> userInfo = loginService.getUserInfo(userId);
        List<SysMenu> userBtnPerm = loginService.getUserBtnPerm(userId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", sysUser);
        map.put("info", userInfo);
        map.put("perm", userBtnPerm);
        loginUser.setPerms(userBtnPerm);
        tokenService.cacheLoginUser(loginUser);
        return AxiosResuit.success(map);
    }
}
