package com.zzr.confidant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzr.confidant.dto.CollectDTO;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.CollectMapper;
import com.zzr.confidant.mapper.CompanyInfoMapper;
import com.zzr.confidant.mapper.PositionMapper;
import com.zzr.confidant.model.Collect;
import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.model.Position;
import com.zzr.confidant.tool.Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 职位收藏表(Collect)表服务接口
 * @author 赵志然
 * @date 2020-03-05 22:50:34
 */
@Service
public class CollectService{

    @Resource
    CollectMapper collectMapper;
    @Resource
    PositionMapper positionMapper;
    @Resource
    CompanyInfoMapper companyInfoMapper;

    /**
     * 用户收藏职位
     * @param userId 当前登陆的用户ID
     * @param positionId 职位ID
     * @return
     */
    public ResultDTO collectPosition(String userId, String positionId) {
        ResultDTO resultDTO = new ResultDTO();
        //首先根据职位id与用户ID查询职位表，判断用户是否已经搜藏了该职位
        List<Collect> list=new ArrayList<>();
        QueryWrapper<Collect> qw = new QueryWrapper<>();
        qw.eq("userId",userId).eq("positionId",positionId);
        list = collectMapper.selectList(qw);
        if(!list.isEmpty()){
            //如果查询出来数据，则说明用户已经收藏了该职位
            resultDTO.setCode(-1);
            resultDTO.setData("您已经收藏过该职位了");
            resultDTO.setData(null);
            return resultDTO;
        }

        //根据职位ID查询职位信息
        Position position = positionMapper.selectById(positionId);
        //根据职位信息中的的公司ID查询公司基本信息
        CompanyInfo companyInfo = companyInfoMapper.selectById(position.getCompanyId());
        //插入数据
        Collect collect = new Collect();
        collect.setId(Tools.getUUID());
        collect.setUserId(userId);
        collect.setPositionId(positionId);
        collect.setPositionName(position.getPositionName());
        collect.setCompanyName(companyInfo.getCompanyName());
        collect.setWorkCity(companyInfo.getCompanyCity());
        collect.setWorkSuffer(position.getWorkSuffer());
        collect.setEducation(position.getEducation());
        collect.setPositionTempt(position.getPositionTempt());
        collect.setPublishTime(position.getPublishTime());
        collect.setReserved1(companyInfo.getId());

        int i = collectMapper.insert(collect);
        if(i==1){
            //插入成功
            resultDTO.setCode(0);
            resultDTO.setData("职位收藏成功");
            resultDTO.setData(null);
        }else{
            //插入失败
            resultDTO.setCode(1);
            resultDTO.setData("职位收藏失败");
            resultDTO.setData(null);
        }

        return resultDTO;
    }

    /**
     * 用户到职位收藏页面 查看收藏职位
     * @param userId 当前登陆用户ID
     * @return
     */
    public List<CollectDTO> selectCollections(String userId) {
        List<CollectDTO> itemList=new ArrayList<>();

        List<Collect> list=new ArrayList<>();
        //根据用户ID查询收藏表
        QueryWrapper<Collect> qw = new QueryWrapper<>();
        qw.eq("userId",userId);
        list = collectMapper.selectList(qw);
        list.stream().forEach(s->{
            CollectDTO collectDTO = new CollectDTO();
            collectDTO.setCollectId(s.getId());
            collectDTO.setPosition(positionMapper.selectById(s.getPositionId()));
            collectDTO.setCompanyInfo(companyInfoMapper.selectById(s.getReserved1()));
            itemList.add(collectDTO);
        });
        return itemList;
    }

    /**
     * 删除职位
     * @param collectId 职位ID
     */
    public void delCollect(String collectId) {
        int i = collectMapper.deleteById(collectId);
    }
}