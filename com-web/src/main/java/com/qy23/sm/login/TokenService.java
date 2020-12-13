package com.qy23.sm.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qy23.sm.cache.CacheService;
import com.qy23.sm.entity.LoginUser;
import com.qy23.sm.entity.SysUser;
import com.qy23.sm.http.AxiosStatus;
import com.qy23.sm.exception.JwtAuthorizationException;
import com.qy23.sm.useragent.ServiceUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @ClassName TokenService
 * @Author 刘伟
 * @Date 2020/10/27 22:19
 * @Description
 * @Version 1.0
 **/
@Component
public class TokenService {

    private String secret = "2242344F45B067EE2F3C294688251F17";

    private long expireTime = 1000 * 60 * 60*24;

    @Autowired
    private CacheService cacheService;

    /**
     * 封装信息
     *
     * @return
     */
    public String createTokenAndLoginUser(SysUser sysUser) {
        LoginUser loginUser = new LoginUser();
        loginUser.setSysUser(sysUser);
        loginUser.setUuid(UUID.randomUUID().toString());
        loginUser.setLoginTime(System.currentTimeMillis());
        setLoginUserAgent(loginUser);
        setExpireTime(loginUser);
        cacheLoginUser(loginUser);
        return createToken(loginUser.getUuid());
    }

    /**
     * 设置Agent相关
     *
     * @param loginUser
     */
    public void setLoginUserAgent(LoginUser loginUser) {
        loginUser.setIpAddr(ServiceUtils.getIpAddr());
        loginUser.setLoginLocation(ServiceUtils.getloginLocation());
        UserAgent userAgent = UserAgent.parseUserAgentString(ServiceUtils.getUserAgent());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
        loginUser.setBroswerNane(userAgent.getBrowser().getName());
    }

    /**
     * 设置时间相关
     */
    public void setExpireTime(LoginUser loginUser) {
        loginUser.setPreviousExecuteTime(System.currentTimeMillis());
        loginUser.setExpireTime(System.currentTimeMillis() + expireTime);
    }

    /**
     * 缓存登录对象
     *
     * @param loginUser
     */
    public void cacheLoginUser(LoginUser loginUser) {
        cacheService.cacheLoginUser(loginUser.getUuid(), loginUser, expireTime);
    }

    /**
     * 生成token
     *
     * @return
     */
    public String createToken(String uuid) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer("伟哥")
                .withClaim("uuid", uuid)
                .sign(algorithm);
        return token;
    }

    /**
     * 解析token
     *
     * @param strToken
     * @return
     */
    public DecodedJWT verifierToken(String strToken) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("伟哥")
                .build();
        DecodedJWT jwt = verifier.verify(strToken);
        return jwt;
    }

    /**
     * 从redis中获取登录对象
     *
     * @param request
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        String tokenUUID = getTokenUUID(token);
        LoginUser loginUser = cacheService.getcacheLoginUser(tokenUUID);
        if (loginUser==null){
            throw new JwtAuthorizationException(AxiosStatus.TOKEN_VALID_FAILURE);
        }
        return loginUser;
    }


    /**
     * 拿到Token的UUID
     *
     * @param token
     * @return
     */
    public String getTokenUUID(String token) {
        DecodedJWT decodedJWT = verifierToken(token);
        return decodedJWT.getClaim("uuid").asString();
    }

    /**
     * 从请求中获得Token
     *
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return authorization.split(" ")[1];
    }

    /**
     * 刷新token过期时间
     */
    public void refreshLoginUserToken(HttpServletRequest request) {
        LoginUser loginUser = this.getLoginUser(request);

        if (loginUser.getPreviousExecuteTime() - System.currentTimeMillis() < 1000 * 60 * 2) {
            this.setExpireTime(loginUser);
            this.cacheLoginUser(loginUser);
        }
    }

    /**
     * 验证token
     *
     * @param request
     * @return
     */
    public boolean tokenAuthorization(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {

            if (verifierToken(authorization.split(" ")[1]) == null) {
                return false;
            }

        } else {
            return false;
        }
        refreshLoginUserToken(request);
        return true;
    }

    /**
     * 清除redis
     */
    public void removeLoginUser(){
        LoginUser loginUser = this.getLoginUser(ServiceUtils.getRequest());
         cacheService.removeCache(loginUser.getUuid());

    }

}
