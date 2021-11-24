package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.BaseSupplier;
import com.qy23.sm.mapper.BaseSupplierMapper;
import com.qy23.sm.service.IBaseSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-17
 */
@Service
@Transactional
public class BaseSupplierServiceImpl implements IBaseSupplierService {

    @Autowired
    private BaseSupplierMapper baseSupplierMapper;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<BaseSupplier> findAll() {
        return baseSupplierMapper.selectList(null);
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @Override
    public BaseSupplier findById(Serializable id) {
        return baseSupplierMapper.selectById(id);
    }

    @Override
    public IPage<BaseSupplier> page(IPage<BaseSupplier> page) {
        return baseSupplierMapper.selectPage(page, null);
    }

    /**
     * 添加
     *
     * @param baseSupplier
     */
    @Override
    public void add(BaseSupplier baseSupplier) {
        baseSupplierMapper.insert(baseSupplier);
    }

    /**
     * 修改
     *
     * @param baseSupplier
     */
    @Override
    public void update(BaseSupplier baseSupplier) {
        baseSupplierMapper.updateById(baseSupplier);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Serializable id) {
        baseSupplierMapper.deleteById(id);
    }
}
