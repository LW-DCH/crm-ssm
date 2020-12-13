package com.qy23.sm.service;

import com.qy23.sm.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
public interface ISysMenuService extends IBaseService<SysMenu> {


    List<SysMenu> getAllMenuTree();
}
