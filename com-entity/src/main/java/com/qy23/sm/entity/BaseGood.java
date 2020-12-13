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
public class BaseGood implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品编码 采用UUId 
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String goodCode;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品型号
     */
    private String goodModel;

    /**
     * 类型id
     */
    private Integer typeId;
    /**
     * 分类名称
     */
    private transient String typeName;
    /**
     * 商品单位 
     */
    private String goodUnit;

    /**
     * 采购价格 成本价 如果价格有浮动 则取平均值
     */
    private Double purchasingPrice;

    /**
     * 上次采购价格
     */
    private Double lastPurchasingPrice;

    /**
     * 售价
     */
    private Double sellPrice;

    /**
     * 库存数量
     */
    private Integer storeNum;

    /**
     * 库存下限
     */
    private Integer minStoreNum;

    /**
     * 0 表示初始化状态 1表示期初库存入仓库 2 表示有进货或者出库单据
     */
    private Integer state;

    /**
     * 生产厂商
     */
    private String producer;

    /**
     * 备注
     */
    private String remarks;


}
