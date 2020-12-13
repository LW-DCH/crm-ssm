package com.qy23.sm.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qy23.sm.annatation.HasPerm;
import com.qy23.sm.entity.BaseCategory;
import com.qy23.sm.entity.BaseGood;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.PageResult;
import com.qy23.sm.service.IBaseCategoryService;
import com.qy23.sm.service.IBaseGoodService;
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
@RequestMapping("good")
public class BaseGoodController {

    @Autowired
    private IBaseGoodService iBaseGoodService;

    @Autowired
    private IBaseCategoryService iBaseCategoryService;
    /**
     * 查询所有
     * @return
     */
    @GetMapping()
    public AxiosResuit list(){
        List<BaseGood> all = iBaseGoodService.findAll();
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
        IPage<BaseGood> page =new Page<>(currentPage,pageSize);
        IPage<BaseGood> page1 = iBaseGoodService.page(page);
        List<BaseGood> records = page1.getRecords();
        records.forEach(item->{
            BaseCategory category = iBaseCategoryService.findById(item.getTypeId());
            item.setTypeName(category.getName());
        });
        return AxiosResuit.success(PageResult.instance(records,page1.getTotal()));
}

    /**
     * 添加
     * @param baseGood
     * @return
     */
    @PostMapping
    @HasPerm(perm = "base:good:add")
    public AxiosResuit add(@RequestBody BaseGood baseGood){
        iBaseGoodService.add(baseGood);
        return AxiosResuit.success();
    }

    /**
     * 修改
     * @param baseGood
     * @return
     */
    @PutMapping
    @HasPerm(perm = "base:good:edit")
    public AxiosResuit updata(@RequestBody BaseGood baseGood){
        iBaseGoodService.update(baseGood);
        return AxiosResuit.success();
    }

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public AxiosResuit findById(@PathVariable Serializable id){
        BaseGood baseGood = iBaseGoodService.findById(id);
        return AxiosResuit.success(baseGood);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public AxiosResuit delete(@PathVariable Serializable id){
       iBaseGoodService.delete(id);
       return AxiosResuit.success();
    }
}
