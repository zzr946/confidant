package com.zzr.confidant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzr.confidant.dto.*;
import com.zzr.confidant.mapper.*;
import com.zzr.confidant.model.*;
import com.zzr.confidant.tool.Tools;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    PositionMapper positionMapper;
    @Resource
    UserMapper userMapper;

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
                                     String companyField, String companyScale, String companyStage, String reserved2, String userId) {
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
        //设置公司愿景
        companyInfo.setReserved2(reserved2);
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
//        Company company=new Company();
        //调用mapper层，将公司介绍信息存入基本信息表中
        int i = companyInfoMapper.saveCompanyDescribe(companyId, companyDescribe);
        if (i == 1) {
            //成功
            //将公司所有信息查询出来，存入公司信息对象中
//            company.setCompanyInfo(companyInfoMapper.selectById(companyId));
//            company.setCompanyTags(companyTagsMapper.selectOne(new QueryWrapper<CompanyTags>().eq("companyId",companyId)));
//            company.setCompanyInitList(companyInitMapper.selectList(new QueryWrapper<CompanyInit>().eq("companyId",companyId)));
//            company.setCompanyProduct(companyProductMapper.selectOne(new QueryWrapper<CompanyProduct>().eq("companyId",companyId)));
            //将用户表中，企业用户认证状态改为已认证
            userMapper.updateAuthentiction(userId);

            resultDTO.setCode(0);
            resultDTO.setMsg("插入成功");
            //将所有公司信息传到前端
            resultDTO.setData(null);
        } else {
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("插入失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 企业用户进入myhome页面，查询出所有公司相关信息
     *
     * @param userId 当前登陆人ID
     * @return
     */
    @Cacheable(cacheNames = "company", key = "'companyAll['+#userId+']'")
    //@Cacheable(value = "user",key = "'userlogin['+#phone+']'")
    @Transactional
    public Company selectCompanyAll(String userId) {
        Company company = new Company();
        //根据用户ID查询出企业ID
        String companyId = companyInfoMapper.selectOne(new QueryWrapper<CompanyInfo>().eq("reserved1", userId)).getId();
        //将公司所有信息查询出来，存入公司信息对象中
        company.setCompanyInfo(companyInfoMapper.selectById(companyId));
        company.setCompanyTags(companyTagsMapper.selectOne(new QueryWrapper<CompanyTags>().eq("companyId", companyId)));
        company.setCompanyInitList(companyInitMapper.selectList(new QueryWrapper<CompanyInit>().eq("companyId", companyId)));
        company.setCompanyProduct(companyProductMapper.selectOne(new QueryWrapper<CompanyProduct>().eq("companyId", companyId)));
        return company;
    }

    /**
     * 修改公司名称、愿景
     *
     * @param companyId      公司ID
     * @param newCompanyName 修改后的公司名
     * @param newReserved2   修改后的公司愿景
     * @return
     */
    @CacheEvict(cacheNames = "company", key = "'companyAll['+#userId+']'")
    @Transactional
    public ResultDTO resetCompanyName(String companyId, String userId, String newCompanyName, String newReserved2) {
        ResultDTO resultDTO = new ResultDTO();
        //调用mapper层修改
        int i = companyInfoMapper.resetCompanyName(companyId, newCompanyName, newReserved2);
        if (i == 1) {
            //修改成功
            resultDTO.setCode(0);
            resultDTO.setMsg("修改成功");
            resultDTO.setData(null);
        } else {
            //修改失败
            resultDTO.setCode(1);
            resultDTO.setMsg("修改失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 修改公司介绍
     *
     * @param companyId          公司id
     * @param userId             当前登陆人id
     * @param newCompanyDescribe 修改后的公司介绍
     * @return
     */
    @CacheEvict(cacheNames = "company", key = "'companyAll['+#userId+']'")
    @Transactional
    public ResultDTO resetCompanyDescribe(String companyId, String userId, String newCompanyDescribe) {
        ResultDTO resultDTO = new ResultDTO();
        int i = companyInfoMapper.resetCompanyDescribe(companyId, newCompanyDescribe);
        if (i == 1) {
            //修改成功
            resultDTO.setCode(0);
            resultDTO.setMsg("修改成功");
            resultDTO.setData(null);
        } else {
            //修改失败
            resultDTO.setCode(1);
            resultDTO.setMsg("修改失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 修改公司地点、领域、规模、网址
     *
     * @param companyId       公司ID
     * @param userId          当前登陆人ID
     * @param newCompanyCity  修改后的公司所在城市
     * @param newCompanyScale 修改后的公司规模
     * @param newCompanyUrl   修改后的公司网址
     * @return
     */
    @CacheEvict(cacheNames = "company", key = "'companyAll['+#userId+']'")
    @Transactional
    public ResultDTO resetAddress(String companyId, String userId, String newCompanyCity, String newCompanyScale, String newCompanyUrl) {
        ResultDTO resultDTO = new ResultDTO();
        int i = companyInfoMapper.resetAddress(companyId, newCompanyCity, newCompanyScale, newCompanyUrl);
        if (i == 1) {
            //修改成功
            resultDTO.setCode(0);
            resultDTO.setMsg("修改成功");
            resultDTO.setData(null);
        } else {
            //修改失败
            resultDTO.setCode(1);
            resultDTO.setMsg("修改失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 修改公司发展阶段
     *
     * @param companyId       公司ID
     * @param userId          当前登陆人ID
     * @param newCompanyStage 修改后的公司发展阶段
     * @return
     */
    @CacheEvict(cacheNames = "company", key = "'companyAll['+#userId+']'")
    @Transactional
    public ResultDTO resetCompanyStage(String companyId, String userId, String newCompanyStage) {
        ResultDTO resultDTO = new ResultDTO();
        int i = companyInfoMapper.resetCompanyStage(companyId, newCompanyStage);
        if (i == 1) {
            //修改成功
            resultDTO.setCode(0);
            resultDTO.setMsg("修改成功");
            resultDTO.setData(null);
        } else {
            //修改失败
            resultDTO.setCode(1);
            resultDTO.setMsg("修改失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 查询公司所有
     *
     * @param companyId 公司ID
     * @return
     */
    public UserLookCompany company(String companyId) {
        UserLookCompany lookCompany = new UserLookCompany();
        //根据公司id查询改所有职位信息
        List<Position> list = positionMapper.selectList(new QueryWrapper<Position>().eq("companyId", companyId).eq("positionState","0"));
        lookCompany.setPositionList(list);
        //将公司所有信息查询出来，存入公司信息对象中
        lookCompany.setCompanyInfo(companyInfoMapper.selectById(companyId));
        lookCompany.setCompanyTags(companyTagsMapper.selectOne(new QueryWrapper<CompanyTags>().eq("companyId", companyId)));
        lookCompany.setCompanyInitList(companyInitMapper.selectList(new QueryWrapper<CompanyInit>().eq("companyId", companyId)));
        lookCompany.setCompanyProduct(companyProductMapper.selectOne(new QueryWrapper<CompanyProduct>().eq("companyId", companyId)));
        return lookCompany;
    }

    /**
     * 用户搜索公司
     *
     * @param page        分页参数 当前页数
     * @param size        分页参数 每页条数
     * @param companyName 公司名称
     * @param city        所在城市
     * @param stage       发展阶段
     * @param field       行业领域
     * @return
     */
    public SelectCompanyItemDTO selectCompanyList(String page, String size, String companyName, String city, String stage, String field) {
        SelectCompanyItemDTO companyItemDTO = new SelectCompanyItemDTO();
        QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<CompanyInfo>();
        if("".equals(city)){city="全国";}
        String companyCity="";
        if("全国".equals(city)){companyCity="";}else{companyCity=city;}

        //构造sql 语句
        queryWrapper.like(StringUtils.isNotEmpty(companyName),"companyName", companyName)
                .like(StringUtils.isNotEmpty(companyCity),"companyCity", city)
                .eq(StringUtils.isNotEmpty(stage),"companyStage", stage)
                .like(StringUtils.isNotEmpty(field),"companyField", field);

        //分页查询
        Page<CompanyInfo> companyInfoPage = new Page<CompanyInfo>(Integer.parseInt(page), Integer.parseInt(size));
        IPage<CompanyInfo> iPage = companyInfoMapper.selectPage(companyInfoPage, queryWrapper);
        Long pageCount = iPage.getPages();//总页数
        Long total = iPage.getTotal();//总记录数
        List<CompanyInfo> companyInfos = iPage.getRecords();//查询出来的数据
        //声明公司ID
        List<Company> companyList=new ArrayList<>();

        companyInfos.stream().forEach(companyInfo->{
            Company company = new Company();
            //获取公司id
            String companyId= companyInfo.getId();
            //根据公司id查询公司其他信息,存到company
            company.setCompanyInfo(companyInfoMapper.selectById(companyId));
            company.setCompanyTags(companyTagsMapper.selectOne(new QueryWrapper<CompanyTags>().eq("companyId",companyId)));
            company.setCompanyInitList(companyInitMapper.selectList(new QueryWrapper<CompanyInit>().eq("companyId",companyId)));
            company.setCompanyProduct(companyProductMapper.selectOne(new QueryWrapper<CompanyProduct>().eq("companyId",companyId)));
            companyList.add(company);
        });

        companyItemDTO.setCompanyName(companyName);
        companyItemDTO.setPage(Long.parseLong(page));
        companyItemDTO.setPageCount(pageCount);
        companyItemDTO.setTotal(total);
        companyItemDTO.setCompanyList(companyList);
        companyItemDTO.setCity(city);
        companyItemDTO.setStage(stage);
        companyItemDTO.setField(field);

        return companyItemDTO;
    }
}