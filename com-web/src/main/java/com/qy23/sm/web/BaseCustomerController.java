package com.qy23.sm.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qy23.sm.entity.BaseCustomer;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.PageResult;
import com.qy23.sm.service.IBaseCustomerService;
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
@RequestMapping("customer")
public class BaseCustomerController {

    @Autowired
    private IBaseCustomerService iBaseCustomerService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping()
    public AxiosResuit list() {
        List<BaseCustomer> all = iBaseCustomerService.findAll();
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
        IPage<BaseCustomer> page = new Page<>(currentPage, pageSize);
        IPage<BaseCustomer> page1 = iBaseCustomerService.page(page);
        return AxiosResuit.success(PageResult.instance(page1.getRecords(), page1.getTotal()));
    }

    /**
     * 添加
     *
     * @param baseCustomer
     * @return
     */
    @PostMapping
    public AxiosResuit add(@RequestBody BaseCustomer baseCustomer) {
        iBaseCustomerService.add(baseCustomer);
        return AxiosResuit.success();
    }

    /**
     * 修改
     *
     * @param baseCustomer
     * @return
     */
    @PutMapping
    public AxiosResuit updata(@RequestBody BaseCustomer baseCustomer) {
        iBaseCustomerService.update(baseCustomer);
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
        BaseCustomer baseCustomer = iBaseCustomerService.findById(id);
        return AxiosResuit.success(baseCustomer);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public AxiosResuit delete(@PathVariable Serializable id) {
        iBaseCustomerService.delete(id);
        return AxiosResuit.success();
    }
}
