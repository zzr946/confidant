package com.zzr.confidant.service;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.CompanyInfoMapper;
import com.zzr.confidant.mapper.CompanyTagsMapper;
import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.model.CompanyTags;
import com.zzr.confidant.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Length;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.Arrays;

/**
 * @description 公司标签表(Companytags)表服务接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Service
public class CompanyTagsService {

    @Resource
    CompanyTagsMapper companyTagsMapper;
    @Resource
    CompanyInfoMapper companyInfoMapper;

    /**
     * 新增标签的方法
     * @param labels
     * @return
     */
    @Transactional
    public ResultDTO saveTags(String labels,String companyId) {
        ResultDTO resultDTO = new ResultDTO();
        CompanyTags tags = new CompanyTags();
        //设置标签
        tags.setId(Tools.getUUID());
        //设置公司id
        tags.setCompanyId(companyId);
        //设置公司名称
        tags.setCompanyName(selectCompanyInfoById(companyId).getCompanyName());
        //设置所有标签字符串
        tags.setCompanyTag(labels);
        //调用mapper层，阿静标签存入数据库
        int count = companyTagsMapper.insert(tags);
        if(count==1){
            //标签存入成功
            resultDTO.setCode(0);
            resultDTO.setMsg("标签设置成功");
            resultDTO.setData(null);
        }else{
            //标签存入失败
            resultDTO.setCode(1);
            resultDTO.setMsg("标签设置失败");
            resultDTO.setData(null);
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