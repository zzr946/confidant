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
 * @description 简历表(resume)表实体类
 * @date 2020-03-05 22:50:36
 */
@ApiModel(description = "简历详情")
@TableName(value = "resume")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    /**
     *简历id
     */
    @TableId(value="id")
    @ApiModelProperty(value = "简历id")
    private String id;

    /**
     * 普通用户id
     */
    @TableField(value = "userId")
    @ApiModelProperty(value = "普通用户id")
    private String userId;

    /**
     * 姓名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "姓名")
    private String name;

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
     * 学制
     */
    @TableField(value = "schoolSystem")
    @ApiModelProperty(value = "学制")
    private String schoolSystem;

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
     * 头像
     */
    @TableField(value = "photo")
    @ApiModelProperty(value = "头像")
    private String photo;

    /**
     * 目前状态
     */
    @TableField(value = "nowState")
    @ApiModelProperty(value = "目前状态")
    private String nowState;

    /**
     * 期望工作地点
     */
    @TableField(value = "hopeWorkplace")
    @ApiModelProperty(value = "期望工作地点")
    private String hopeWorkplace;

    /**
     * 工作性质
     */
    @TableField(value = "jobType")
    @ApiModelProperty(value = "工作性质")
    private String jobType;

    /**
     * 期望职位
     */
    @TableField(value = "hopePosition")
    @ApiModelProperty(value = "期望职位")
    private String hopePosition;

    /**
     * 期望月薪
     */
    @TableField(value = "hopeSalary")
    @ApiModelProperty(value = "期望月薪")
    private String hopeSalary;

    /**
     * 曾工作的公司
     */
    @TableField(value = "oldCompany")
    @ApiModelProperty(value = "曾工作的公司")
    private String oldCompany;

    /**
     * 曾担任职位
     */
    @TableField(value = "oldPosition")
    @ApiModelProperty(value = "曾担任职位")
    private String oldPosition;

    /**
     * 原工作开始年份
     */
    @TableField(value = "oldJobStartYear")
    @ApiModelProperty(value = "原工作开始年份")
    private String oldJobStartYear;

    /**
     * 原工作开始月份
     */
    @TableField(value = "oldJobStartMonth")
    @ApiModelProperty(value = "原工作开始月份")
    private String oldJobStartMonth;

    /**
     * 原工作结束年份
     */
    @TableField(value = "oldJobEndYear")
    @ApiModelProperty(value = "原工作结束年份")
    private String oldJobEndYear;

    /**
     * 原工作结束月份
     */
    @TableField(value = "oldJobEndMonth")
    @ApiModelProperty(value = "原工作结束月份")
    private String oldJobEndMonth;

    /**
     * 项目名称
     */
    @TableField(value = "projectName")
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * 项目中担任角色
     */
    @TableField(value = "role")
    @ApiModelProperty(value = "项目中担任角色")
    private String role;

    /**
     * 项目开始年份
     */
    @TableField(value = "pojStartYear")
    @ApiModelProperty(value = "项目开始年份")
    private String pojStartYear;

    /**
     * 项目开始月份
     */
    @TableField(value = "pojStartMonth")
    @ApiModelProperty(value = "项目开始月份")
    private String pojStartMonth;

    /**
     * 项目结束年份
     */
    @TableField(value = "pojEndYear")
    @ApiModelProperty(value = "项目结束年份")
    private String pojEndYear;

    /**
     * 项目结束月份
     */
    @TableField(value = "pojEndMonth")
    @ApiModelProperty(value = "项目结束月份")
    private String pojEndMonth;

    /**
     * 项目描述
     */
    @TableField(value = "pojDescribe")
    @ApiModelProperty(value = "项目描述")
    private String pojDescribe;

    /**
     * 毕业学校
     */
    @TableField(value = "school")
    @ApiModelProperty(value = "毕业学校")
    private String school;

    /**
     * 学历
     */
    @TableField(value = "education")
    @ApiModelProperty(value = "学历")
    private String education;

    /**
     * 所学专业名称
     */
    @TableField(value = "majorName")
    @ApiModelProperty(value = "所学专业名称")
    private String majorName;

    /**
     * 上学开始年份
     */
    @TableField(value = "studyStartYear")
    @ApiModelProperty(value = "上学开始年份")
    private String studyStartYear;

    /**
     * 上学结束年份
     */
    @TableField(value = "studyEndYear")
    @ApiModelProperty(value = "上学结束年份")
    private String studyEndYear;

    /**
     * 自我描述
     */
    @TableField(value = "autognosis")
    @ApiModelProperty(value = "自我描述")
    private String autognosis;

    /**
     * 作品展示网址
     */
    @TableField(value = "worksUrl")
    @ApiModelProperty(value = "作品展示网址")
    private String worksUrl;

    /**
     * 作品描述
     */
    @TableField(value = "worksDescribe")
    @ApiModelProperty(value = "作品描述")
    private String worksDescribe;

    /**
     * 简历状态
     */
    @TableField(value = "resumeState")
    @ApiModelProperty(value = "简历状态")
    private String resumeState;

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