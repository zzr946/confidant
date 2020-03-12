package com.zzr.confidant.mapper;

import com.zzr.confidant.model.CompanyProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * @description 公司产品表(Companyproduct)表Mapper接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
public interface CompanyProductMapper extends BaseMapper<CompanyProduct> {

    @Update("update companyproduct set ProductName=#{newProductName},ProductUrl=${newProductUrl},ProductDescribe=#{newProductDescribe} where companyId=#{companyId}")
    int resetProduct(String companyId, String newProductName, String newProductUrl, String newProductDescribe);
}