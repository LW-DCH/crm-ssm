package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.SysOperLog;
import com.qy23.sm.mapper.SysOperLogMapper;
import com.qy23.sm.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-29
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {
    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public List<SysOperLog> findAll() {
        return sysOperLogMapper.selectList(null);
    }

    @Override
    public SysOperLog findById(Serializable id) {
        return sysOperLogMapper.selectById(id);
    }

    @Override
    public IPage<SysOperLog> page(IPage<SysOperLog> page) {
        return null;
    }

    @Override
    public void add(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }

    @Override
    public void update(SysOperLog sysOperLog) {
        sysOperLogMapper.updateById(sysOperLog);
    }

    @Override
    public void delete(Serializable id) {
        sysOperLogMapper.deleteById(id);
    }
}
