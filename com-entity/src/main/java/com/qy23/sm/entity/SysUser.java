package com.qy23.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qy23.sm.anntation.SexValues;
import com.qy23.sm.group.AddGrout;
import com.qy23.sm.group.UpdataGrout;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @Null(groups = {AddGrout.class},message = "添加时ID必须为空")
    @NotNull(groups = {UpdataGrout.class},message = "修改时ID不能为空")
    private Long userId;

    /**
     * 用户账号
     */
    @NotBlank(groups = {AddGrout.class,UpdataGrout.class})
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{5,7}$",message = "用户账号格式不正确",groups = {AddGrout.class,UpdataGrout.class})
    private String userName;

    /**
     * 用户昵称
     */

    @NotBlank(message = "你大爷的没给值",groups = {AddGrout.class,UpdataGrout.class})
    private String nickName;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式错误",groups = {AddGrout.class,UpdataGrout.class})
    @NotBlank(groups = {AddGrout.class,UpdataGrout.class})
    private String email;

    /**
     * 手机号码
     */
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",groups = {AddGrout.class,UpdataGrout.class})
    @NotBlank(groups = {AddGrout.class,UpdataGrout.class})
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @SexValues(values = {"0","1"},message = "性别错误",groups = {AddGrout.class,UpdataGrout.class})
    private String sex;

    /**
     * 头像地址
     */
    @URL(message = "请传头像有效地址",groups = {AddGrout.class,UpdataGrout.class})
    @NotBlank(groups = {AddGrout.class,UpdataGrout.class})
    private String avatar;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 帐号状态（1正常 0停用）
     */
    private Boolean status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户角色ID
     */
    private transient String roleIds;

}
