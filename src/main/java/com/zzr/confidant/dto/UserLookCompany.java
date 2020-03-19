package com.zzr.confidant.dto;

import com.zzr.confidant.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 用户查看公司发布的职位信息所需要的数据
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLookCompany {
    /**
     * 职位信息
     */
    private List<Position> positionList;

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
