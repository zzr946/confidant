package com.zzr.confidant.service;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.CompanyInfoMapper;
import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.tool.Tools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description 公司表基本信息表(Companyinfo)表服务接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Service
public class CompanyInfoService {
    @Resource
    CompanyInfoMapper companyInfoMapper;
    /**
     * 公司注册第一步，将公司基本信息存入数据库
     * @param companyName 公司名
     * @param companyLogo 公司logo名称
     * @param companyUrl 公司网址
     * @param companyCity 公司所在城市
     * @param companyField 公司所属行业领域
     * @param companyScale 公司规模
     * @param companyStage 公司发展阶段
     * @return
     */
    @Transactional
    public ResultDTO saveCompanyInfo(String companyName, String companyLogo, String companyUrl, String companyCity,
                                     String companyField, String companyScale, String companyStage) {
        ResultDTO resultDTO = new ResultDTO();
//        System.out.println("公司名称："+companyName);
//        System.out.println("公司logo文件对象："+companyLogo);
//        System.out.println("公司网址："+companyUrl);
//        System.out.println("公司所在城市："+companyCity);
//        System.out.println("公司所属行业领域："+companyField);
//        System.out.println("公司规模："+companyScale);
//        System.out.println("公司发展阶段："+companyStage);

        CompanyInfo companyInfo=new CompanyInfo();
        companyInfo.setId(Tools.getUUID());
        companyInfo.setCompanyName(companyName);
        companyInfo.setCompanyLogo(companyLogo);
        companyInfo.setCompanyUrl(companyUrl);
        companyInfo.setCompanyCity(companyCity);
        companyInfo.setCompanyField(companyField);
        companyInfo.setCompanyScale(companyScale);
        companyInfo.setCompanyStage(companyStage);
        //调用mpper 层将数据写入数据库
        int count = companyInfoMapper.insert(companyInfo);
        if(count==1){
            //插入成功
            resultDTO.setCode(0);
            resultDTO.setMsg("插入成功");
            //将新插入的数据返回
            resultDTO.setData(companyInfoMapper.selectById(companyInfo.getId()));
        }else{
            //插入失败
            resultDTO.setCode(1);
            resultDTO.setMsg("插入失败");
            resultDTO.setData(null);
        }
        return resultDTO;

    }
}