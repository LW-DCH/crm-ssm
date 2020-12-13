package com.qy23.sm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qy23.sm.entity.BaseCategory;
import com.qy23.sm.mapper.BaseCategoryMapper;
import com.qy23.sm.service.IBaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
public class BaseCategoryServiceImpl implements IBaseCategoryService {

    @Autowired
    private BaseCategoryMapper baseCategoryMapper;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<BaseCategory> findAll() {
        return baseCategoryMapper.selectList(null);
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @Override
    public BaseCategory findById(Serializable id) {
        return baseCategoryMapper.selectById(id);
    }

    @Override
    public IPage<BaseCategory> page(IPage<BaseCategory> page) {
        IPage<BaseCategory> baseCategoryIPage = baseCategoryMapper.selectPage(page, null);
        baseCategoryIPage.getRecords().forEach(item -> {
            Integer pId = item.getPId();
            if (pId.equals(0)) {
                item.setPidName("一级分类");
            } else {
                item.setPidName(this.findById(pId).getName());
            }
        });
        return baseCategoryIPage;
    }

    /**
     * 添加
     *
     * @param baseCategory
     */
    @Override
    public void add(BaseCategory baseCategory) {
        baseCategoryMapper.insert(baseCategory);
    }

    /**
     * 修改
     *
     * @param baseCategory
     */
    @Override
    public void update(BaseCategory baseCategory) {
        baseCategoryMapper.updateById(baseCategory);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Serializable id) {
        baseCategoryMapper.deleteById(id);
    }

    /**
     * 递归获得分类
     *
     * @return
     */
    @Override
    public List<BaseCategory> getCategoryTree() {
        //查询所有分类
        List<BaseCategory> all = this.findAll();
        //遍历所有的一级分类
        List<BaseCategory> collect = all.stream().filter(baseCategory -> baseCategory.getPId() == 0).collect(Collectors.toList());
        collect.forEach(item -> {
            getCategoryChild(item, all);
        });

        return collect;
    }

    /**
     * 找一级分类的子类
     *
     * @return
     */
    public void getCategoryChild(BaseCategory category, List<BaseCategory> all) {
        List<BaseCategory> collect = all.stream().filter(category1 -> category1.getPId() == category.getId()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)) {
            category.setChildren(collect);
            collect.forEach(item -> {
                getCategoryChild(item, all);
            });
        }
    }

}
