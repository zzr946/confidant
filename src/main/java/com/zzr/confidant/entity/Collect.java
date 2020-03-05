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
 * @description 职位收藏表(collect)表实体类
 * @date 2020-03-05 22:50:32
 */
@ApiModel(description = "职位收藏")
@TableName(value = "collect")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Collect {
    /**
     * 收藏id
     */
    @TableId(value="id")
    @ApiModelProperty(value = "收藏id")
    private String id;

    /**
     * 普通用户id
     */
    @TableField(value = "userId")
    @ApiModelProperty(value = "普通用户id")
    private String userId;

    /**
     * 职位id
     */
    @TableField(value = "positionId")
    @ApiModelProperty(value = "职位id")
    private String positionId;

    /**
     * 职位名称
     */
    @TableField(value = "positionName")
    @ApiModelProperty(value = "职位名称")
    private String positionName;

    /**
     * 职位所属公司名称
     */
    @TableField(value = "companyName")
    @ApiModelProperty(value = "职位所属公司名称")
    private String companyName;

    /**
     * 工作城市
     */
    @TableField(value = "workCity")
    @ApiModelProperty(value = "工作城市")
    private String workCity;

    /**
     * 工作经验
     */
    @TableField(value = "workSuffer")
    @ApiModelProperty(value = "工作经验")
    private String workSuffer;

    /**
     * 学历要求
     */
    @TableField(value = "education")
    @ApiModelProperty(value = "学历要求")
    private String education;

    /**
     * 职位诱惑
     */
    @TableField(value = "positionTempt")
    @ApiModelProperty(value = "职位诱惑")
    private String positionTempt;

    /**
     * 职位发布时间
     */
    @TableField(value = "publishTime")
    @ApiModelProperty(value = "职位发布时间")
    private String publishTime;

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