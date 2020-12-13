package com.qy23.sm.web;


import com.qy23.sm.entity.SysRole;
import com.qy23.sm.group.UpdataGrout;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private ISysRoleService iSysRoleService;
   

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping()
    public AxiosResuit list() {
        List<SysRole> all = iSysRoleService.findAll();
        return AxiosResuit.success(all);
    }



    /**
     * 添加
     *
     * @param SysRole
     * @return
     */
    @PostMapping

    public AxiosResuit add( @RequestBody SysRole SysRole)  {
        iSysRoleService.add(SysRole);
        return AxiosResuit.success();
    }

    /**
     * 修改
     *
     * @param SysRole
     * @return
     */
    @PutMapping
    public AxiosResuit updata(@Validated(UpdataGrout.class) @RequestBody SysRole SysRole) {
        iSysRoleService.update(SysRole);
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
        SysRole SysRole = iSysRoleService.findById(id);
        return AxiosResuit.success(SysRole);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public AxiosResuit delete( @PathVariable Serializable id) {
        iSysRoleService.delete(id);
        return AxiosResuit.success();
    }


}
