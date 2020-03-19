package com.zzr.confidant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 搜索公司 用户根据公司名称搜索，后台返回数据显示在companyList页面
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectCompanyItemDTO {
    /**
     * 公司名称 用户输入的搜索条件
     */
    private String companyName;

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
     * 公司item
     */
    private List<Company> companyList;

    /**
     * 搜索条件 公司所在城市
     */
    private String city;

    /**
     * 搜索条件 发展阶段
     */
    private String stage;

    /**
     * 搜索条件 行业领域
     */
    private String field;
}
