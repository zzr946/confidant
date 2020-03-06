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
 * @description 用户表(user)表实体类
 * @date 2020-03-05 22:50:36
 */
@ApiModel(description = "用户登陆信息")
@TableName(value = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户id
     */
    @TableId(value = "id")
    @ApiModelProperty(value = "用户id")
    private String id;

    /**
     * 电话号码,登录账号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "电话号码,登录账号")
    private String phone;

    /**
     * 登录密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "登录密码")
    private String password;

    /**
     * 用户类型
     */
    @TableField(value = "userType")
    @ApiModelProperty(value = "用户类型")
    private String userType;

    /**
     * 企业用户是否认证
     */
    @TableField(value = "authentication")
    @ApiModelProperty(value = "企业用户是否认证")
    private String authentication;

    /**
     * 登录状态
     */
    @TableField(value = "loginState")
    @ApiModelProperty(value = "登录状态")
    private String loginState;

    /**
     * 预留字段1
     */
    @TableField(value = "reserved1")
    @ApiModelProperty(value = "预留字段1")
    private String reserved1;

    /**
     * 预留字段1
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