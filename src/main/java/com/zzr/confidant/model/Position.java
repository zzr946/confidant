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
 * @description 职位表(position)表实体类
 * @date 2020-03-05 22:50:36
 */
@ApiModel(description = "职位信息")
@TableName(value = "position")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    /**
     *职位id
     */
    @TableId(value="id")
    @ApiModelProperty(value = "职位id")
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
     * 职位类型
     */
    @TableField(value = "positionType")
    @ApiModelProperty(value = "职位类型")
    private String positionType;

    /**
     * 职位名称
     */
    @TableField(value = "positionName")
    @ApiModelProperty(value = "职位名称")
    private String positionName;

    /**
     * 职位所属部门
     */
    @TableField(value = "department")
    @ApiModelProperty(value = "职位所属部门")
    private String department;

    /**
     * 工作性质
     */
    @TableField(value = "jobType")
    @ApiModelProperty(value = "工作性质")
    private String jobtTpe;

    /**
     * 最低月薪
     */
    @TableField(value = "leastSalary")
    @ApiModelProperty(value = "最低月薪")
    private Integer leastSalary;

    /**
     * 最高月薪
     */
    @TableField(value = "mostSalary")
    @ApiModelProperty(value = "最高月薪")
    private Integer mostSalary;

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
     * 职位描述
     */
    @TableField(value = "positionDescribe")
    @ApiModelProperty(value = "职位描述")
    private String positionDescribe;

    /**
     * 联系方式邮箱
     */
    @TableField(value = "contactWay")
    @ApiModelProperty(value = "联系方式邮箱")
    private String contactWay;

    /**
     * 发布时间
     */
    @TableField(value = "publishTime")
    @ApiModelProperty(value = "发布时间")
    private String publishTime;

    /**
     * 职位状态
     */
    @TableField(value = "positionState")
    @ApiModelProperty(value = "公司id")
    private String positionState;

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