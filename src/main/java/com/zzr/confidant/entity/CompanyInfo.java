package com.zzr.confidant.entity;

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
 * @description 公司基本信息表(companyinfo)表实体类
 * @date 2020-03-05 22:50:36
 */
@ApiModel(description = "公司基本信息")
@TableName(value = "companyinfo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfo {
    /**
     *公司id
     */
    @TableId(value="id")
    @ApiModelProperty(value = "公司id")
    private String id;
    /**
     * 公司名称
     */
    @TableField(value = "companyName")
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 公司Logo
     */
    @TableField(value = "companyLogo")
    @ApiModelProperty(value = "公司Logo")
    private String companyLogo;

    /**
     * 公司网址
     */
    @TableField(value = "companyUrl")
    @ApiModelProperty(value = "公司网址")
    private String companyUrl;

    /**
     * 公司所在城市
     */
    @TableField(value = "companyCity")
    @ApiModelProperty(value = "公司所在城市")
    private String companyCity;

    /**
     * 所属行业领域
     */
    @TableField(value = "companyField")
    @ApiModelProperty(value = "所属行业领域")
    private String companyField;

    /**
     * 公司规模
     */
    @TableField(value = "companyScale")
    @ApiModelProperty(value = "公司规模")
    private String companyScale;

    /**
     * 发展阶段
     */
    @TableField(value = "companyStage")
    @ApiModelProperty(value = "发展阶段")
    private String companyStage;

    /**
     * 公司介绍
     */
    @TableField(value = "companyDescribe")
    @ApiModelProperty(value = "公司介绍")
    private String companyDescribe;

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