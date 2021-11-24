package com.qy23.sm.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qy23.sm.entity.BaseSupplier;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.PageResult;
import com.qy23.sm.service.IBaseSupplierService;
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
@RequestMapping("supplier")
public class BaseSupplierController {

    @Autowired
    private IBaseSupplierService iBaseSupplierService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping()
    public AxiosResuit list() {
        List<BaseSupplier> all = iBaseSupplierService.findAll();
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
        IPage<BaseSupplier> page = new Page<>(currentPage, pageSize);
        IPage<BaseSupplier> page1 = iBaseSupplierService.page(page);
        return AxiosResuit.success(PageResult.instance(page1.getRecords(), page1.getTotal()));
    }

    /**
     * 添加
     *
     * @param baseSupplier
     * @return
     */
    @PostMapping
    public AxiosResuit add(@RequestBody BaseSupplier baseSupplier) {
        iBaseSupplierService.add(baseSupplier);
        return AxiosResuit.success();
    }

    /**
     * 修改
     *
     * @param baseSupplier
     * @return
     */
    @PutMapping
    public AxiosResuit updata(@RequestBody BaseSupplier baseSupplier) {
        iBaseSupplierService.update(baseSupplier);
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
        BaseSupplier baseSupplier = iBaseSupplierService.findById(id);
        return AxiosResuit.success(baseSupplier);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public AxiosResuit delete(@PathVariable Serializable id) {
        iBaseSupplierService.delete(id);
        return AxiosResuit.success();
    }
}
