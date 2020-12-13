package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.BaseUnit;
import com.qy23.sm.mapper.BaseUnitMapper;
import com.qy23.sm.service.IBaseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-17
 */
@Service
@Transactional
public class BaseUnitServiceImpl implements IBaseUnitService {

    @Autowired
    private BaseUnitMapper baseUnitMapper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<BaseUnit> findAll() {
        return baseUnitMapper.selectList(null);
    }

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    @Override
    public BaseUnit findById(Serializable id) {
        return baseUnitMapper.selectById(id);
    }

    @Override
    public IPage<BaseUnit> page(IPage<BaseUnit> page) {
        return baseUnitMapper.selectPage(page,null);
    }

    /**
     * 添加
     * @param baseUnit
     */
    @Override
    public void add(BaseUnit baseUnit) {
        baseUnitMapper.insert(baseUnit);
    }

    /**
     * 修改
     * @param baseUnit
     */
    @Override
    public void update(BaseUnit baseUnit) {
        baseUnitMapper.updateById(baseUnit);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Serializable id) {
        baseUnitMapper.deleteById(id);
    }
}
