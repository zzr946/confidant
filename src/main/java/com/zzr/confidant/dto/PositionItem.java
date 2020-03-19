package com.zzr.confidant.dto;

import com.zzr.confidant.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 搜索职位 返回到页面的 职位Item
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionItem {
    /**
     * 职位信息
     */
    private Position position;

    /**
     * 公司基本信息
     */
    private CompanyInfo companyInfo;

    /**
     * 标签
     */
    private CompanyTags companyTags;

    /**
     * 创始人集合
     */
    private List<CompanyInit> companyInitList;

    /**
     * 产品
     */
    private CompanyProduct companyProduct;
}
