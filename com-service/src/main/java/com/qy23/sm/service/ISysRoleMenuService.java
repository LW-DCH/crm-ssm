package com.qy23.sm.service;

import com.qy23.sm.entity.SysRoleMenu;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
public interface ISysRoleMenuService extends IBaseService<SysRoleMenu>  {

    List<SysRoleMenu> findSysRoleMenuByRoleId(Serializable roleId);
    void deleteByRoleId(Serializable id);
}
