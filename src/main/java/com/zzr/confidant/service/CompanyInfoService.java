package com.zzr.confidant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzr.confidant.dto.Company;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.CompanyInfoMapper;
import com.zzr.confidant.mapper.CompanyInitMapper;
import com.zzr.confidant.mapper.CompanyProductMapper;
import com.zzr.confidant.mapper.CompanyTagsMapper;
import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.model.CompanyInit;
import com.zzr.confidant.model.CompanyProduct;
import com.zzr.confidant.model.CompanyTags;
import com.zzr.confidant.tool.Tools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * @author 赵志然
 * @description 公司表基本信息表(Companyinfo)表服务接口
 * @date 2020-03-05 22:50:36
 */
@Service
public class CompanyInfoService {
    @Resource
    CompanyInfoMapper companyInfoMapper;
    @Resource
    CompanyTagsMapper companyTagsMapper;
    @Resource
    CompanyInitMapper companyInitMapper;
    @Resource
    CompanyProductMapper companyProductMapper;

    /**
     * 公司注册第一步，将公司基本信息存入数据库
     *
     * @param companyName  公司名
     * @param companyLogo  公司logo名称
     * @param companyUrl   公司网址
     * @param companyCity  公司所在城市
     * @param companyField 公司所属行业领域
     * @param companyScale 公司规模
     * @param companyStage 公司发展阶段
     * @return
     */
    @Transactional
    public ResultDTO saveCompanyInfo(String companyName, String companyLogo, String companyUrl, String companyCity,
                                     String companyField, String companyScale, String companyStage, String userId) {
        ResultDTO resultDTO = new ResultDTO();
//        System.out.println("公司名称："+companyName);
//        System.out.println("公司logo文件对象："+companyLogo);
//        System.out.println("公司网址："+companyUrl);
//        System.out.println("公司所在城市："+companyCity);
//        System.out.println("公司所属行业领域："+companyField);
//        System.out.println("公司规模："+companyScale);
//        System.out.println("公司发展阶段："+companyStage);

        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setId(Tools.getUUID());
        companyInfo.setCompanyName(companyName);
        companyInfo.setCompanyLogo(companyLogo);
        companyInfo.setCompanyUrl(companyUrl);
        companyInfo.setCompanyCity(companyCity);
        companyInfo.setCompanyField(companyField);
        companyInfo.setCompanyScale(companyScale);
        companyInfo.setCompanyStage(companyStage);
        //设置当前登陆的用户id，将用户与公司绑定起来
        companyInfo.setReserved1(userId);
        //调用mpper 层将数据写入数据库
        int count = companyInfoMapper.insert(companyInfo);
        if (count == 1) {
            //插入成功
            resultDTO.setCode(0);
            resultDTO.setMsg("插入成功");
            //将新插入的数据返回
            resultDTO.setData(companyInfoMapper.selectById(companyInfo.getId()));
        } else {
            //插入失败
            resultDTO.setCode(1);
            resultDTO.setMsg("插入失败");
            resultDTO.setData(null);
        }
        return resultDTO;

    }

    /**
     * 新增公司简介
     *
     * @param companyId       公司id
     * @param userId          当前登陆人ID
     * @param companyDescribe 公司介绍
     * @return
     */
    @Transactional
    public ResultDTO saveCompanyDescribe(String companyId, String userId, String companyDescribe) {
        ResultDTO resultDTO = new ResultDTO();
        //公司所有信息DTO对象
        Company company=new Company();
        //调用mapper层，将公司介绍信息存入基本信息表中
        int i = companyInfoMapper.saveCompanyDescribe(companyId, companyDescribe);
        if (i == 1) {
            //成功,将公司所有信息查询出来，存入公司信息对象中
            company.setCompanyInfo(companyInfoMapper.selectById(companyId));
            company.setCompanyTags(companyTagsMapper.selectOne(new QueryWrapper<CompanyTags>().eq("companyId",companyId)));
            company.setCompanyInit(companyInitMapper.selectOne(new QueryWrapper<CompanyInit>().eq("companyId",companyId)));
            company.setCompanyProduct(companyProductMapper.selectOne(new QueryWrapper<CompanyProduct>().eq("companyId",companyId)));

            resultDTO.setCode(0);
            resultDTO.setMsg("插入成功");
            //将锁哦与信息传到前端
            resultDTO.setData(company);
        } else {
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("插入失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }
}