package com.zzr.confidant.service;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.CompanyInfoMapper;
import com.zzr.confidant.mapper.CompanyProductMapper;
import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.model.CompanyProduct;
import com.zzr.confidant.tool.Tools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description 公司产品表(Companyproduct)表服务接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Service
public class CompanyProductService {

    @Resource
    CompanyProductMapper companyProductMapper;
    @Resource
    CompanyInfoMapper companyInfoMapper;

    /**
     * 添加公司产品
     * @param companyId 公司ID
     * @param userId 当前登陆人ID
     * @param filename 产品logo名
     * @param productName 产品名称
     * @param productUrl 产品地址
     * @param productDescribe 产品介绍
     * @return
     */
    @Transactional
    public ResultDTO saveProduct(String companyId, String userId, String filename, String productName, String productUrl, String productDescribe) {
        ResultDTO resultDTO=new ResultDTO();
        CompanyProduct product = new CompanyProduct();
        product.setId(Tools.getUUID());
        product.setCompanyId(companyId);
        product.setCompanyName(selectCompanyInfoById(companyId).getCompanyName());
        product.setProductLogo(filename);
        product.setProductName(productName);
        product.setProductUrl(productUrl);
        product.setProductDescribe(productDescribe);
        //调用mapper层存储
        int i = companyProductMapper.insert(product);
        if(i==1){
            //成功
            resultDTO.setCode(0);
            resultDTO.setMsg("添加成功！");
            resultDTO.setData(product);
        }else{
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("失败！");
            resultDTO.setData(product);
        }
        return resultDTO;
    }

    /**
     * 根据公司id查询公司基本信息
     * @param companyId 公司id
     * @return
     */
    @Transactional(readOnly = true)
    public CompanyInfo selectCompanyInfoById(String companyId){
        return companyInfoMapper.selectById(companyId);
    }
}