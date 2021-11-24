package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.SysRoleMenu;
import com.qy23.sm.mapper.SysRoleMenuMapper;
import com.qy23.sm.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
@Service
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleMenu> findAll() {
        return sysRoleMenuMapper.selectList(null);
    }

    @Override
    public SysRoleMenu findById(Serializable id) {

        return sysRoleMenuMapper.selectById(id);
    }

    @Override
    public IPage<SysRoleMenu> page(IPage<SysRoleMenu> page) {
        return null;
    }

    @Override
    public void add(SysRoleMenu sysRoleMenu) {
        sysRoleMenuMapper.insert(sysRoleMenu);
    }

    @Override
    public void update(SysRoleMenu sysRoleMenu) {
        sysRoleMenuMapper.updateById(sysRoleMenu);
    }

    @Override
    public void delete(Serializable id) {
        sysRoleMenuMapper.deleteById(id);
    }

    @Override
    public void deleteByRoleId(Serializable id) {
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysRoleMenu::getRoleId, id);
        sysRoleMenuMapper.delete(wrapper);
    }

    @Override
    public List<SysRoleMenu> findSysRoleMenuByRoleId(Serializable roleId) {
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysRoleMenu::getRoleId, roleId);
        return sysRoleMenuMapper.selectList(wrapper);
    }
}
