package com.qy23.sm.login;

import com.qy23.sm.async.AsyncFactory;
import com.qy23.sm.async.AsyncManager;
import com.qy23.sm.entity.SysMenu;
import com.qy23.sm.entity.SysUser;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.AxiosStatus;
import com.qy23.sm.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName LoginService
 * @Author 刘伟
 * @Date 2020/10/28 10:45
 * @Description登录操作
 * @Version 1.0
 **/
@Component
public class LoginService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private TokenService tokenService;

    public AxiosResuit doLogin(String userName, String password) {

        if (StringUtils.isEmpty(userName)) {
            //用户名为空
            AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("1", AxiosStatus.USERNAME_NOT_EMPTY.getMessage(), userName));
            return AxiosResuit.error(AxiosStatus.USERNAME_NOT_EMPTY);
        }
        if (StringUtils.isEmpty(password)) {
            //密码名为空
            AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("1", AxiosStatus.PASSWORD_NOT_EMPTY.getMessage(), userName));
            return AxiosResuit.error(AxiosStatus.PASSWORD_NOT_EMPTY);
        }
        //通过用户名查询用户
        SysUser user = iSysUserService.findByUserName(userName);
        if (user == null) {
            //用户名不正确
            AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("1", AxiosStatus.USERNAMEORPASSWORD_NOT_SURE.getMessage(), userName));
            return AxiosResuit.error(AxiosStatus.USERNAMEORPASSWORD_NOT_SURE);
        }
        //查询用户信息
        boolean matches = bCryptPasswordEncoder.matches(password, user.getPassword());

        if (!matches) {
            //密码错误
            AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("1", AxiosStatus.USERNAMEORPASSWORD_NOT_SURE.getMessage(), userName));
            return AxiosResuit.error(AxiosStatus.USERNAMEORPASSWORD_NOT_SURE);
        }

        AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("0", "登录成功", userName));
        String token = tokenService.createTokenAndLoginUser(user);
        return AxiosResuit.success(token);
    }

    /**
     * 获取菜单权限相关
     * @param userId
     */
    public List<SysMenu> getUserInfo(Long userId) {
        List<SysMenu> userRouter = iSysUserService.findUserRouter(userId);
        return userRouter;
    }

    /**
     * 获取按钮权限相关
     * @param userId
     * @return
     */
    public List<SysMenu> getUserBtnPerm(Long userId) {
        List<SysMenu> userBtnPerm = iSysUserService.findUserBtnPerm(userId);
        return userBtnPerm;
    }
}
