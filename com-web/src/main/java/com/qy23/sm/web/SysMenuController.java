package com.qy23.sm.web;


import com.qy23.sm.entity.SysMenu;
import com.qy23.sm.group.AddGrout;
import com.qy23.sm.group.UpdataGrout;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.service.ISysMenuService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
@RequestMapping("menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService iSysMenuService;
   

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping()
    public AxiosResuit list() {
        List<SysMenu> allMenuTree = iSysMenuService.getAllMenuTree();
        return AxiosResuit.success(allMenuTree);
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
     return AxiosResuit.success();
    }

    /**
     * 添加
     *
     * @param SysMenu
     * @return
     */
    @PostMapping

    public AxiosResuit add(@Validated(AddGrout.class) @RequestBody SysMenu SysMenu) throws IOException, TemplateException {
        iSysMenuService.add(SysMenu);
        return AxiosResuit.success();
    }

    /**
     * 修改
     *
     * @param SysMenu
     * @return
     */
    @PutMapping
    public AxiosResuit updata(@Validated(UpdataGrout.class) @RequestBody SysMenu SysMenu) {
        iSysMenuService.update(SysMenu);
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
        SysMenu SysMenu = iSysMenuService.findById(id);
        return AxiosResuit.success(SysMenu);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public AxiosResuit delete( @PathVariable Serializable id) {
        iSysMenuService.delete(id);
        return AxiosResuit.success();
    }


}
