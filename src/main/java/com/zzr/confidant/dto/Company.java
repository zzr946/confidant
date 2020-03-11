package com.zzr.confidant.dto;

import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.model.CompanyInit;
import com.zzr.confidant.model.CompanyProduct;
import com.zzr.confidant.model.CompanyTags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 公司相关的所有信息
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private CompanyInfo companyInfo;

    private CompanyTags companyTags;

    private CompanyInit companyInit;

    private CompanyProduct companyProduct;

}
