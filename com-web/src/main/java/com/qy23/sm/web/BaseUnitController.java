package com.qy23.sm.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qy23.sm.entity.BaseUnit;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.PageResult;
import com.qy23.sm.service.IBaseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-17
 */
@RestController
@RequestMapping("unit")
public class BaseUnitController {

    @Autowired
    private IBaseUnitService iBaseUnitService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping()
    public AxiosResuit list(){
        List<BaseUnit> all = iBaseUnitService.findAll();
        return AxiosResuit.success(all);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("page")
    public AxiosResuit page(@RequestParam(defaultValue = "1") int currentPage,
                            @RequestParam(defaultValue = "1") int pageSize){
        IPage<BaseUnit> page =new Page<>(currentPage,pageSize);
        IPage<BaseUnit> page1 = iBaseUnitService.page(page);
        return AxiosResuit.success(PageResult.instance(page1.getRecords(),page1.getTotal()));
    }

    /**
     * 添加
     * @param baseUnit
     * @return
     */
    @PostMapping
    public AxiosResuit add(@RequestBody BaseUnit baseUnit){
        iBaseUnitService.add(baseUnit);
        return AxiosResuit.success();
    }

    /**
     * 修改
     * @param baseUnit
     * @return
     */
    @PutMapping
    public AxiosResuit updata(@RequestBody BaseUnit baseUnit){
        iBaseUnitService.update(baseUnit);
        return AxiosResuit.success();
    }

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public AxiosResuit findById(@PathVariable Serializable id){
        BaseUnit baseUnit = iBaseUnitService.findById(id);
        return AxiosResuit.success(baseUnit);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public AxiosResuit delete(@PathVariable Serializable id){
        iBaseUnitService.delete(id);
        return AxiosResuit.success();
    }
}
