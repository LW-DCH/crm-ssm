package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.SysMenu;
import com.qy23.sm.mapper.SysMenuMapper;
import com.qy23.sm.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> findAll() {

        return sysMenuMapper.selectList(null);
    }

    @Override
    public SysMenu findById(Serializable id) {

        return sysMenuMapper.selectById(id);
    }

    @Override
    public IPage<SysMenu> page(IPage<SysMenu> page) {

        return null;
    }

    @Override
    public void add(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
    }

    @Override
    public void update(SysMenu sysMenu) {
         sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void delete(Serializable id) {
       sysMenuMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> getAllMenuTree() {
        List<SysMenu> all = this.findAll();
        //过滤出父ID为0的值
        List<SysMenu> collect = all.stream().filter(sysMenu -> sysMenu.getParentId().longValue()==0).collect(Collectors.toList());

        collect.forEach(sysMenu -> {
            getMenuChild(sysMenu,all);
        });


        return collect;
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
}
