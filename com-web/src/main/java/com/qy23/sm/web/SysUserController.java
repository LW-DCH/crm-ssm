package com.qy23.sm.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qy23.sm.async.AsyncFactory;
import com.qy23.sm.async.AsyncManager;
import com.qy23.sm.email.EmailService;
import com.qy23.sm.entity.SysRole;
import com.qy23.sm.entity.SysUser;
import com.qy23.sm.group.AddGrout;
import com.qy23.sm.group.UpdataGrout;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.PageResult;
import com.qy23.sm.service.ISysUserRoleService;
import com.qy23.sm.service.ISysUserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-17
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private ISysUserRoleService iSysUserRoleService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping()
    public AxiosResuit list() {
        List<SysUser> all = iSysUserService.findAll();
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
        IPage<SysUser> page = new Page<>(currentPage, pageSize);
        IPage<SysUser> page1 = iSysUserService.page(page);
        return AxiosResuit.success(PageResult.instance(page1.getRecords(), page1.getTotal()));
    }

    /**
     * 添加
     *
     * @param sysUser
     * @return
     */
    @PostMapping

    public AxiosResuit add(@Validated(AddGrout.class) @RequestBody SysUser sysUser) throws IOException, TemplateException {
        String encode = bCryptPasswordEncoder.encode("123456");
        sysUser.setPassword(encode);
        iSysUserService.add(sysUser);

        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("user.ftl", "UTF-8");
        Map<String, String> map = new HashMap<>(16);
        map.put("userID", sysUser.getUserId() + "");
        map.put("userName", sysUser.getUserName());
        map.put("email", sysUser.getEmail());
        map.put("phone", sysUser.getPhone());
        map.put("password", "123456");
        StringWriter writer = new StringWriter();
        template.process(map, writer);
        AsyncManager.getInstance().executeTask(AsyncFactory.executeEmail(sysUser.getEmail(), writer.toString()));
        return AxiosResuit.success();
    }

    /**
     * 修改
     *
     * @param sysUser
     * @return
     */
    @PutMapping
    public AxiosResuit updata(@Validated(UpdataGrout.class) @RequestBody SysUser sysUser) {
        iSysUserService.update(sysUser);
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
        SysUser sysUser = iSysUserService.findById(id);
        return AxiosResuit.success(sysUser);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public AxiosResuit delete(@PathVariable Serializable id) {
        iSysUserService.delete(id);
        return AxiosResuit.success();
    }

    @GetMapping("{userId}/roles")
    public AxiosResuit getRoleByUserId(@PathVariable Serializable userId) {
        List<SysRole> sysRoles = iSysUserRoleService.getRoleByUserId(userId);
        return AxiosResuit.success(sysRoles);
    }

    @DeleteMapping("{userId}/role/{roleId}")
    public AxiosResuit deleteRoleById(@PathVariable Serializable userId, @PathVariable Serializable roleId) {
        iSysUserRoleService.deleteRoleById(userId, roleId);
        return AxiosResuit.success();
    }
}
