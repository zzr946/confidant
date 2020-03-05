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
 * @description 公司标签表(companytags)表实体类
 * @date 2020-03-05 22:50:36
 */
@ApiModel(description = "公司标签")
@TableName(value = "companytags")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyTags {
    /**
     *公司标签id
     */
    @TableId(value="id")
    @ApiModelProperty(value = "公司标签id")
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
     * 标签内容
     */
    @TableField(value = "companyTag")
    @ApiModelProperty(value = "标签内容")
    private String companyTag;

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