package com.qy23.sm.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qy23.sm.entity.BaseCategory;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.PageResult;
import com.qy23.sm.service.IBaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-17
 */
@RestController
@RequestMapping("category")
public class BaseCategoryController {

    @Autowired
    private IBaseCategoryService iBaseCategoryService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping()
    public AxiosResuit list() {
        List<BaseCategory> all = iBaseCategoryService.findAll();
        return AxiosResuit.success(all);
    }

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("page")
    public AxiosResuit page(@RequestParam(defaultValue = "1") int currentPage,
                            @RequestParam(defaultValue = "1") int pageSize) {
        IPage<BaseCategory> page = new Page<>(currentPage, pageSize);
        IPage<BaseCategory> page1 = iBaseCategoryService.page(page);
        return AxiosResuit.success(PageResult.instance(page1.getRecords(), page1.getTotal()));
    }

    /**
     * 添加
     *
     * @param baseCategory
     * @return
     */
    @PostMapping
    public AxiosResuit add(@RequestBody BaseCategory baseCategory) {
        iBaseCategoryService.add(baseCategory);
        return AxiosResuit.success();
    }

    /**
     * 修改
     *
     * @param baseCategory
     * @return
     */
    @PutMapping
    public AxiosResuit updata(@RequestBody BaseCategory baseCategory) {
        iBaseCategoryService.update(baseCategory);
        return AxiosResuit.success();
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public AxiosResuit findById(@PathVariable Serializable id) {
        BaseCategory baseCategory = iBaseCategoryService.findById(id);
        return AxiosResuit.success(baseCategory);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public AxiosResuit delete(@PathVariable Serializable id) {
        iBaseCategoryService.delete(id);
        return AxiosResuit.success();
    }

    /**
     * 递归查询分类
     *
     * @return
     */
    @GetMapping("categoryTree")
    public AxiosResuit categoryTree() {
        List<BaseCategory> categoryTree = iBaseCategoryService.getCategoryTree();
        return AxiosResuit.success(categoryTree);
    }
}
