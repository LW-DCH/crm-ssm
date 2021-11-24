package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.BaseCategory;
import com.qy23.sm.entity.BaseGood;
import com.qy23.sm.mapper.BaseGoodMapper;
import com.qy23.sm.service.IBaseCategoryService;
import com.qy23.sm.service.IBaseGoodService;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class BaseGoodServiceImpl implements IBaseGoodService {

    @Autowired
    private BaseGoodMapper baseGoodMapper;

    @Autowired
    private IBaseCategoryService iBaseCategoryService;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<BaseGood> findAll() {
        List<BaseGood> baseGoods = baseGoodMapper.selectList(null);
        baseGoods.forEach(item -> {
            BaseCategory category = iBaseCategoryService.findById(item.getTypeId());
            item.setTypeName(category.getName());
        });
        return baseGoods;
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @Override
    public BaseGood findById(Serializable id) {
        return baseGoodMapper.selectById(id);
    }

    @Override
    public IPage<BaseGood> page(IPage<BaseGood> page) {

        return baseGoodMapper.selectPage(page, null);
    }

    /**
     * 添加
     *
     * @param baseGood
     */
    @Override
    public void add(BaseGood baseGood) {
        baseGoodMapper.insert(baseGood);
    }

    /**
     * 修改
     *
     * @param baseGood
     */
    @Override
    public void update(BaseGood baseGood) {
        baseGoodMapper.updateById(baseGood);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Serializable id) {
        baseGoodMapper.deleteById(id);
    }
}
