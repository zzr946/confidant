package com.zzr.confidant.service;

import com.zzr.confidant.mapper.CompanyInfoMapper;
import com.zzr.confidant.mapper.CompanyInitMapper;
import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.model.CompanyInit;
import com.zzr.confidant.tool.Tools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @description 公司创始人表(Companyinit)表服务接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Service
public class CompanyInitService {

    @Resource
    CompanyInitMapper companyInitMapper;

    @Resource
    CompanyInfoMapper companyInfoMapper;

    /**
     * 将创始人信息存入数据库
     * @param companyId 公司ID
     * @param filename 创始人头像 重命名后的图片名称
     * @param founderName 创始人姓名
     * @param founderPosition 创始人目前职位
     * @param founderDescribe 创始人简介
     * @return
     */
    public int saveFounder(String companyId, String filename, String founderName, String founderPosition, String founderDescribe) {
        CompanyInit companyInit = new CompanyInit();
        companyInit.setId(Tools.getUUID());
        companyInit.setCompanyId(companyId);
        companyInit.setCompanyName(selectCompanyInfoById(companyId).getCompanyName());
        companyInit.setFounderName(founderName);
        companyInit.setFounderPosition(founderPosition);
        companyInit.setFounderPhoto(filename);
        companyInit.setFounderDescribe(founderDescribe);
        //调用mapper，将数据存入数据库
        return companyInitMapper.insert(companyInit);
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