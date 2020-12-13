package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.SysLoginLog;
import com.qy23.sm.mapper.SysLoginLogMapper;
import com.qy23.sm.service.ISysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-28
 */
@Service
@Transactional
public class SysLoginLogServiceImpl implements ISysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public List<SysLoginLog> findAll() {
        return sysLoginLogMapper.selectList(null);
    }

    @Override
    public SysLoginLog findById(Serializable id) {
        return sysLoginLogMapper.selectById(id);
    }

    @Override
    public IPage<SysLoginLog> page(IPage<SysLoginLog> page) {
        return null;
    }

    @Override
    public void add(SysLoginLog sysLoginLog) {
        sysLoginLogMapper.insert(sysLoginLog);
    }

    @Override
    public void update(SysLoginLog sysLoginLog) {
        sysLoginLogMapper.updateById(sysLoginLog);
    }

    @Override
    public void delete(Serializable id) {
        sysLoginLogMapper.deleteById(id);
    }
}
