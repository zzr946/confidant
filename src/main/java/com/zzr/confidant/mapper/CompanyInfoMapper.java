package com.zzr.confidant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzr.confidant.model.CompanyInfo;

/**
 * @description 公司表基本信息表(Companyinfo)表Mapper接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
public interface CompanyInfoMapper extends BaseMapper<CompanyInfo> {

    int saveCompanyDescribe(String companyId, String companyDescribe);
}