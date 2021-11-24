package com.qy23.sm.service;

import com.qy23.sm.entity.SysRole;
import com.qy23.sm.entity.SysUserRole;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
public interface ISysUserRoleService extends IBaseService<SysUserRole> {
    List<SysRole> getRoleByUserId(Serializable userId);

    void deleteRoleById(Serializable userId, Serializable roleId);

    void deleteRoleByUserId(Serializable userId);
}
