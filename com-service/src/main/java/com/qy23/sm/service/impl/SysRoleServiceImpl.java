package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.SysMenu;
import com.qy23.sm.entity.SysRole;
import com.qy23.sm.entity.SysRoleMenu;
import com.qy23.sm.mapper.SysRoleMapper;
import com.qy23.sm.service.ISysMenuService;
import com.qy23.sm.service.ISysRoleMenuService;
import com.qy23.sm.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ISysRoleMenuService iSysRoleMenuService;
    @Autowired
    private ISysMenuService iSysMenuService;

    @Override
    public List<SysRole> findAll() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByAsc(SysRole::getRoleSort);
        return sysRoleMapper.selectList(wrapper);
    }

    @Override
    public SysRole findById(Serializable id) {
        ArrayList<Long> list = new ArrayList<>();
        List<SysMenu> sysMenus = new ArrayList<>();
        SysRole sysRole = sysRoleMapper.selectById(id);
        List<SysRoleMenu> sysRoleMenuByRoleId = iSysRoleMenuService.findSysRoleMenuByRoleId(sysRole.getRoleId());
        sysRoleMenuByRoleId.forEach(sysRoleMenu -> {
            SysMenu sysMenu = iSysMenuService.findById(sysRoleMenu.getMenuId());
            sysMenus.add(sysMenu);
        });
        //排除目录
        List<SysMenu> collect = sysMenus.stream().filter(sysMenu -> !sysMenu.getMenuType().equalsIgnoreCase("M")).collect(Collectors.toList());
        //查找最底层的元素
        collect.forEach(sysMenu -> {
            if (!haChildren(sysMenu, collect)) {
                list.add(sysMenu.getMenuId());
            }
        });
        sysRole.setMenuIds(list);
        return sysRole;
    }

    public boolean haChildren(SysMenu sysMenu, List<SysMenu> all) {
        return all.stream().anyMatch(sysMenu1 -> sysMenu1.getParentId().longValue() == sysMenu.getMenuId().longValue());
    }


    @Override
    public IPage<SysRole> page(IPage<SysRole> page) {

        return null;
    }

    @Override
    public void add(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
        List<Long> menuIds = sysRole.getMenuIds();
        if (!CollectionUtils.isEmpty(menuIds)) {
            menuIds.forEach(item -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenu.setMenuId(item);
                iSysRoleMenuService.add(sysRoleMenu);
            });
        }
    }

    @Override
    public void update(SysRole sysRole) {
        sysRoleMapper.updateById(sysRole);
        iSysRoleMenuService.deleteByRoleId(sysRole.getRoleId());
        List<Long> menuIds = sysRole.getMenuIds();
        if (!CollectionUtils.isEmpty(menuIds)) {
            menuIds.forEach(item -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(item);
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                iSysRoleMenuService.add(sysRoleMenu);
            });
        }
    }

    @Override
    public void delete(Serializable id) {
        sysRoleMapper.deleteById(id);
    }
}
