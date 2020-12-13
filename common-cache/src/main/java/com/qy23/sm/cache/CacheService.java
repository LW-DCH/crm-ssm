package com.qy23.sm.cache;

import com.qy23.sm.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheService
 * @Author 刘伟
 * @Date 2020/10/31 20:15
 * @Description
 * @Version 1.0
 **/
@Component
public class CacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JSONUtils jsonUtils;

    /**
     * 存缓存
     * @param key
     * @param obj
     */
    public void cacheObj(String key,Object obj){

        stringRedisTemplate.opsForValue().set(key,jsonUtils.obj2str(obj));
    }

    /**
     * 存缓存+有效时间
     * @param key
     * @param obj
     */
    public void cacheObj(String key,Object obj,long expireTime){
        stringRedisTemplate.opsForValue().set(key,jsonUtils.obj2str(obj),expireTime,TimeUnit.MILLISECONDS);
    }

    /**
     * 获取对象
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getcacheObj(String key,Class<T> tClass){
        String s = stringRedisTemplate.opsForValue().get(key);
        T t = jsonUtils.str2obj(s, tClass);
        return t;
    }

    /**
     * 获取登录对象
     * @param key
     * @return
     */
    public LoginUser getcacheLoginUser(String key){
        LoginUser loginUser = this.getcacheObj(key, LoginUser.class);
        return loginUser;
    }


    /**
     * 缓存登录对象
     * @param key
     * @param loginUser
     */
   public void cacheLoginUser(String key,LoginUser loginUser){
        this.cacheObj(key,loginUser);
   }

    /**
     * 缓存登录对象+有效时间
     * @param key
     * @param loginUser
     */
    public void cacheLoginUser(String key,LoginUser loginUser,long expireTime){
       this.cacheObj(key,loginUser,expireTime);
    }

    /**
     * 清除缓存
     * @param key
     */
    public void removeCache(String key){
        stringRedisTemplate.delete(key);
    }


}
