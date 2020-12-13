package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.SysRole;
import com.qy23.sm.entity.SysUserRole;
import com.qy23.sm.mapper.SysUserRoleMapper;
import com.qy23.sm.service.ISysRoleService;
import com.qy23.sm.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private ISysRoleService iSysRoleService;

    @Override
    public List<SysUserRole> findAll() {
        return sysUserRoleMapper.selectList(null);
    }

    @Override
    public SysUserRole findById(Serializable id) {

        return sysUserRoleMapper.selectById(id);
    }

    @Override
    public IPage<SysUserRole> page(IPage<SysUserRole> page) {
        return null;
    }

    @Override
    public void add(SysUserRole sysUserRole) {
         sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public void update(SysUserRole sysUserRole) {
       sysUserRoleMapper.updateById(sysUserRole);
    }

    @Override
    public void delete(Serializable id) {
      sysUserRoleMapper.deleteById(id);
    }

    @Override
    public void deleteRoleByUserId(Serializable userId) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getUserId,userId);
        sysUserRoleMapper.delete(wrapper);
    }

    @Override
    public void deleteRoleById(Serializable userId, Serializable roleId) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getRoleId,roleId).eq(SysUserRole::getUserId,userId);
        sysUserRoleMapper.delete(wrapper);
    }

    @Override
    public List<SysRole> getRoleByUserId(Serializable userId) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getUserId,userId);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(wrapper);
        ArrayList<SysRole> roles = new ArrayList<>();
        sysUserRoles.forEach(sysUserRole -> {
           SysRole role = iSysRoleService.findById(sysUserRole.getRoleId());
           roles.add(role);
        });
        return roles;
    }
}
