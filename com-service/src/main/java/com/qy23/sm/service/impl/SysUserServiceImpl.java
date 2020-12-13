package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.SysMenu;
import com.qy23.sm.entity.SysRoleMenu;
import com.qy23.sm.entity.SysUser;
import com.qy23.sm.entity.SysUserRole;
import com.qy23.sm.mapper.SysRoleMenuMapper;
import com.qy23.sm.mapper.SysUserMapper;
import com.qy23.sm.mapper.SysUserRoleMapper;
import com.qy23.sm.service.ISysMenuService;
import com.qy23.sm.service.ISysUserRoleService;
import com.qy23.sm.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysUserRoleService iSysUserRoleService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private ISysMenuService iSysMenuService;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectList(null);
    }

    @Override
    public SysUser findById(Serializable id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public IPage<SysUser> page(IPage<SysUser> page) {

        return sysUserMapper.selectPage(page, null);
    }

    @Override
    public void add(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
        //添加用户之后添加用户角色
        String[] split = sysUser.getRoleIds().split("A");
        Arrays.asList(split).forEach(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getUserId());
            sysUserRole.setRoleId(Long.parseLong(item));
            iSysUserRoleService.add(sysUserRole);
        });

    }

    @Override
    public void update(SysUser sysUser) {
        sysUserMapper.updateById(sysUser);
        iSysUserRoleService.getRoleByUserId(sysUser.getUserId());
        String[] split = sysUser.getRoleIds().split("A");
        Arrays.asList(split).forEach(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getUserId());
            sysUserRole.setRoleId(Long.parseLong(item));
            iSysUserRoleService.add(sysUserRole);
        });

    }

    @Override
    public void delete(Serializable id) {
        sysUserMapper.deleteById(id);
    }


    @Override
    public SysUser findByUserName(String userName) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUserName, userName);
        List<SysUser> sysUsers = sysUserMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        } else {
            return sysUsers.get(0);
        }
    }

    @Override
    public List<SysMenu> getRouterTreeByUserId(Long userId) {

        List<SysMenu> menus = new ArrayList<>();
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getUserId, userId);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(wrapper);
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        sysUserRoles.forEach(sysUserRole -> {
            queryWrapper.lambda().eq(SysRoleMenu::getRoleId, sysUserRole.getRoleId());
            List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(queryWrapper);
            sysRoleMenus.forEach(sysRoleMenu -> {
                SysMenu sysMenu = iSysMenuService.findById(sysRoleMenu.getMenuId());
                menus.add(sysMenu);
            });
        });

        return menus;
    }

    public void getMenuChild(SysMenu sysMenu,List<SysMenu> all){
        List<SysMenu> collect = all.stream().filter(sysMenu1 -> sysMenu1.getParentId().equals(sysMenu.getMenuId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)){
            sysMenu.setChildren(collect);
        }
        collect.forEach(sysMenu2 ->{
            getMenuChild(sysMenu2,all);
        } );
    }


    @Override
    public List<SysMenu> findUserRouter(Long userId) {
        List<SysMenu> menus = this.getRouterTreeByUserId(userId);
        //排除按钮之外的菜单
        List<SysMenu> collect = menus.stream().filter(sysMenu1 -> !sysMenu1.getMenuType().equalsIgnoreCase("F")).collect(Collectors.toList());
        //一级菜单
        List<SysMenu> list = collect.stream().filter(sysMenu2 -> sysMenu2.getParentId().longValue() == 0).collect(Collectors.toList());
        list.forEach(list1->{
            getMenuChild(list1,collect);
        });
        return list;
    }

    @Override
    public List<SysMenu> findUserBtnPerm(Long userId) {
        List<SysMenu> menus = this.getRouterTreeByUserId(userId);
        List<SysMenu> sysMenus = menus.stream().filter(sysMenu1 -> sysMenu1.getMenuType().equalsIgnoreCase("F")).collect(Collectors.toList());
        return sysMenus;
    }
}
