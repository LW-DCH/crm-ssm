package com.qy23.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 刘伟
 * @since 2020-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseSupplier implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商位置
     */
    private String address;

    /**
     * 练习人
     */
    private String contact;

    /**
     * 供应商公司名称
     */
    private String name;

    /**
     * 联系方式
     */
    private String phone;

    private String remark;


}
