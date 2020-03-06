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
 * @description 简历投递表(deliver)表实体类
 * @date 2020-03-05 22:50:36
 */
@ApiModel(description = "简历投递记录")
@TableName(value = "deliver")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deliver {
    /**
     *简历投递记录id
     */
    @TableId(value="id")
    @ApiModelProperty(value = "简历投递记录id")
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
     * 简历id
     */
    @TableField(value = "resumeId")
    @ApiModelProperty(value = "简历id")
    private String resumeId;

    /**
     * 简历投递人id
     */
    @TableField(value = "userId")
    @ApiModelProperty(value = "简历投递人id")
    private String userId;

    /**
     * 简历投递人姓名
     */
    @TableField(value = "userName")
    @ApiModelProperty(value = "简历投递人姓名")
    private String userName;

    /**
     * 性别
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 最高学历
     */
    @TableField(value = "maxEducation")
    @ApiModelProperty(value = "最高学历")
    private String maxEducation;

    /**
     * 应聘职位名称
     */
    @TableField(value = "positionName")
    @ApiModelProperty(value = "应聘职位名称")
    private String positionName;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 简历投递时间
     */
    @TableField(value = "deliverTime")
    @ApiModelProperty(value = "简历投递时间")
    private String deliverTime;

    /**
     * 简历状态
     */
    @TableField(value = "deliverState")
    @ApiModelProperty(value = "简历状态")
    private String deliverState;

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