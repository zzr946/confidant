package com.zzr.confidant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 用户根据职位搜索，后台返回数据显示在list页面
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectJobItemDTO {

    /**
     * 用户搜索的职位名称（用户输入的）
     */
    private String positionName;

    /**
     * 分页查询 当前页数
     */
    private Long page;

    /**
     * 分页查询 总页数
     */
    private Long pageCount;

    /**
     * 分页查询 总记录数
     */
    private Long total;

    /**
     * 职位 公司 item
     */
    private List<PositionItem> positionItemList;


    /**
     * 月薪范围
     */
    private String salary;
    /**
     * 工作经验
     */
    private String suffer;
    /**
     * 学历
     */
    private String education;
    /**
     * 工作性质
     */
    private String jobType;


}
