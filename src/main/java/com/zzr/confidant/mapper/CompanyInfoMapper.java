package com.zzr.confidant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzr.confidant.model.CompanyInfo;
import org.apache.ibatis.annotations.Update;

/**
 * @description 公司表基本信息表(Companyinfo)表Mapper接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
public interface CompanyInfoMapper extends BaseMapper<CompanyInfo> {

    int saveCompanyDescribe(String companyId, String companyDescribe);

    @Update("update companyinfo set companyName=#{newCompanyName},reserved2=#{newReserved2} where id=#{companyId}")
    int resetCompanyName(String companyId, String newCompanyName, String newReserved2);

    @Update("update companyinfo set companyDescribe=#{newCompanyDescribe} where id=#{companyId}")
    int resetCompanyDescribe(String companyId, String newCompanyDescribe);

    @Update("update companyinfo set companyCity=#{newCompanyCity},companyScale=#{newCompanyScale},companyUrl=#{newCompanyUrl} where id=#{companyId}")
    int resetAddress(String companyId, String newCompanyCity, String newCompanyScale, String newCompanyUrl);

    @Update("update companyinfo set companyStage=#{newCompanyStage} where id=#{companyId}")
    int resetCompanyStage(String companyId, String newCompanyStage);
}