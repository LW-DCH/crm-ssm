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
public class BaseCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户公司名称
     */
    private String name;

    /**
     * 客户名称
     */
    private String contact;

    /**
     * 客户地址
     */
    private String address;

    /**
     * 客户联系方式
     */
    private String phone;

    private String remarks;


}
