package com.qy23.sm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {

    List<T> findAll();

    T findById(Serializable id);


    IPage<T> page(IPage<T> page);

    void add(T t);

    void update(T t);

    void delete(Serializable id);
}
