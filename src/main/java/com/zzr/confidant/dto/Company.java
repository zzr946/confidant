package com.zzr.confidant.dto;

import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.model.CompanyInit;
import com.zzr.confidant.model.CompanyProduct;
import com.zzr.confidant.model.CompanyTags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 公司相关的所有信息
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
    private static final long serialVersionUID = -6878053406541801993L;
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
