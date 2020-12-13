package com.qy23.sm.service;

import com.qy23.sm.entity.SysMenu;
import com.qy23.sm.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
public interface ISysUserService extends IBaseService<SysUser> {


    SysUser findByUserName(String userName);

    List<SysMenu> getRouterTreeByUserId(Long userId);
    List<SysMenu> findUserRouter(Long userId);
    List<SysMenu> findUserBtnPerm(Long userId);



}
