package com.qy23.sm.service;

import com.qy23.sm.entity.BaseCategory;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-17
 */
public interface IBaseCategoryService extends IBaseService<BaseCategory> {

    /**
     * 递归获得分类
     *
     * @return
     */
    List<BaseCategory> getCategoryTree();
}
