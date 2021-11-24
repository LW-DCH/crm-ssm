package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.BaseCustomer;
import com.qy23.sm.mapper.BaseCustomerMapper;
import com.qy23.sm.service.IBaseCustomerService;
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
public class BaseCustomerServiceImpl implements IBaseCustomerService {

    @Autowired
    private BaseCustomerMapper baseCustomerMapper;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<BaseCustomer> findAll() {
        return baseCustomerMapper.selectList(null);
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @Override
    public BaseCustomer findById(Serializable id) {
        return baseCustomerMapper.selectById(id);
    }


    @Override
    public IPage<BaseCustomer> page(IPage<BaseCustomer> page) {
        return baseCustomerMapper.selectPage(page, null);
    }

    /**
     * 添加
     *
     * @param baseCustomer
     */
    @Override
    public void add(BaseCustomer baseCustomer) {
        baseCustomerMapper.insert(baseCustomer);
    }

    /**
     * 修改
     *
     * @param baseCustomer
     */
    @Override
    public void update(BaseCustomer baseCustomer) {
        baseCustomerMapper.updateById(baseCustomer);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Serializable id) {
        baseCustomerMapper.deleteById(id);
    }
}
