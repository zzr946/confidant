package com.zzr.confidant.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 赵志然
 * @description 公司产品表(companyproduct)表实体类
 * @date 2020-03-05 22:50:36
 */
@ApiModel(description = "公司产品表")
@TableName(value = "companyproduct")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProduct {
    /**
     *公司产品id
     */
    @TableId(value="id")
    @ApiModelProperty(value = "公司产品id")
    private String id;

    /**
     * 公司id
     */
    @TableField(value = "companyId")
    @ApiModelProperty(value = "公司id")
    private String companyId;

    /**
     * 公司名称
     */
    @TableField(value = "companyName")
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 产品海报
     */
    @TableField(value = "productLogo")
    @ApiModelProperty(value = "产品海报")
    private String productLogo;

    /**
     * 产品名称
     */
    @TableField(value = "productName")
    @ApiModelProperty(value = "产品名称")
    private String productName;

    /**
     * 产品网址
     */
    @TableField(value = "productUrl")
    @ApiModelProperty(value = "产品网址")
    private String productUrl;

    /**
     * 产品介绍
     */
    @TableField(value = "productDescribe")
    @ApiModelProperty(value = "产品介绍")
    private String productDescribe;

    /**
     * 预留字段1
     */
    @TableField(value = "reserved1")
    @ApiModelProperty(value = "预留字段1")
    private String reserved1;

    /**
     * 预留字段2
     */
    @TableField(value = "reserved2")
    @ApiModelProperty(value = "预留字段2")
    private String reserved2;

    /**
     * 预留字段3
     */
    @TableField(value = "reserved3")
    @ApiModelProperty(value = "预留字段3")
    private String reserved3;

    /**
     * 预留字段4
     */
    @TableField(value = "reserved4")
    @ApiModelProperty(value = "预留字段4")
    private String reserved4;

    /**
     * 预留字段5
     */
    @TableField(value = "reserved5")
    @ApiModelProperty(value = "预留字段5")
    private String reserved5;

}