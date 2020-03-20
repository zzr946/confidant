package com.zzr.confidant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzr.confidant.dto.ManagerToudi;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.dto.ToudiDTO;
import com.zzr.confidant.mapper.*;
import com.zzr.confidant.model.*;
import com.zzr.confidant.tool.Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description 简历投递表(Deliver)表服务接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Service
public class DeliverService {

    @Resource
    DeliverMapper deliverMapper;
    @Resource
    PositionMapper positionMapper;
    @Resource
    CompanyInfoMapper companyInfoMapper;
    @Resource
    ResumeMapper resumeMapper;

    /**
     * 用户投递简历
     * @param userId 用户ID
     * @param positionId 职位ID
     * @return
     */
    public ResultDTO toudi(String userId, String positionId) {
        ResultDTO result = new ResultDTO();
        //判断用户是否有简历
        List<Resume> resumes=new ArrayList<>();
        QueryWrapper<Resume> resumeQueryWrapper = new QueryWrapper<>();
        resumeQueryWrapper.eq("userId",userId);
        resumes = resumeMapper.selectList(resumeQueryWrapper);
        if(resumes.isEmpty()){
            //为空则说明用户没用简历，提示先添加简历
            result.setCode(-2);
            result.setMsg("无可用简历");
            result.setData(null);
            return result;
        }

        //先判断该用户是否已经投递过该职位
        List<Deliver> list=new ArrayList<>();
        QueryWrapper<Deliver> qw = new QueryWrapper<>();
        qw.eq("userId",userId).eq("reserved1",positionId);
        list = deliverMapper.selectList(qw);
        if(!list.isEmpty()){
            //不为空则说明该职位已经投递过了
            result.setCode(-1);
            result.setMsg("重复投递");
            result.setData(null);
            return result;
        }


        Deliver deliver = new Deliver();
        //根据用户ID查询简历表
        Resume resume = resumeMapper.selectOne(new QueryWrapper<Resume>().eq("userId", userId));
        //根据职位ID查询职位表
        Position position = positionMapper.selectById(positionId);
        //根据职位表中的公司id查询公司信息
        CompanyInfo companyInfo = companyInfoMapper.selectById(position.getCompanyId());

        //设置简历投递DTO
        deliver.setId(Tools.getUUID());
        deliver.setCompanyId(companyInfo.getId());
        deliver.setCompanyName(companyInfo.getCompanyName());
        deliver.setResumeId(resume.getId());
        deliver.setUserId(userId);
        deliver.setUserName(resume.getName());
        deliver.setSex(resume.getSex());
        deliver.setMaxEducation(resume.getMaxEducation());
        deliver.setPositionName(position.getPositionName());
        deliver.setPhone(resume.getPhone());
        deliver.setEmail(resume.getEmail());
        deliver.setDeliverTime(Tools.dateToStr("yyyy-MM-dd HH:mm",new Date()));
        deliver.setDeliverState("0");//刚投递的简历在公司那边显示待定
        deliver.setReserved1(positionId);//设置投递的职位ID
        int i = deliverMapper.insert(deliver);
        if(i==1){
            //成功
            result.setCode(0);
            result.setMsg("简历投递成功");
            result.setData(null);
        }else{
            //失败
            result.setCode(1);
            result.setMsg("简历投递失败");
            result.setData(null);
        }
        return result;
    }

    /**
     * 用户查看投递的简历状态
     * @param userId 用户ID
     * @return
     */
    public List<ToudiDTO> myToudi(String userId) {
        List<ToudiDTO> list=new ArrayList<>();
        List<Deliver> toudis=new ArrayList<>();
        //根据用户ID查询简历投递表
        toudis=deliverMapper.selectList(new QueryWrapper<Deliver>().eq("userId",userId));
        toudis.stream().forEach(s->{
            ToudiDTO toudiDTO = new ToudiDTO();
            toudiDTO.setDeliver(s);
            toudiDTO.setCompanyInfo(companyInfoMapper.selectById(s.getCompanyId()));
            toudiDTO.setPosition(positionMapper.selectById(s.getReserved1()));
            list.add(toudiDTO);
        });
        return list;
    }

    /**
     * 用户删除不合适的简历投递记录
     * @param deliverId 投递记录ID
     * @param userId 用户ID
     * @return
     */
    public List<ToudiDTO> delToudi(String deliverId, String userId) {
        QueryWrapper<Deliver> qw = new QueryWrapper<>();
        qw.eq("id",deliverId).eq("userId",userId);
        deliverMapper.delete(qw);
        return myToudi(userId);
    }

    /**
     * 企业用户查询投递的简历
     * @param userId
     * @return
     */
    public List<ManagerToudi> managerToudi(String userId) {
        List<ManagerToudi> list = new ArrayList<>();
        //查询公司基本信息
        CompanyInfo companyInfo = companyInfoMapper.selectOne(new QueryWrapper<CompanyInfo>().eq("reserved1", userId));
        //根据公司ID查询所有收到的简历
        List<Deliver> toudis = deliverMapper.selectList(new QueryWrapper<Deliver>().eq("companyid", companyInfo.getId()).eq("deliverState","0"));

        toudis.stream().forEach(s->{
            ManagerToudi toudi = new ManagerToudi();
            //查询投递的简历信息
            Resume resume = resumeMapper.selectById(s.getResumeId());
            //设置简历信息
            toudi.setResume(resume);
            toudi.setDeliver(s);
            list.add(toudi);
        });
        return list;
    }

    /**
     * 通知面试 将简历投递表状态修改为1
     * @param toudiId 投递表 ID
     */
    public void inform(String toudiId) {
        deliverMapper.inform(toudiId);
    }

    /**
     * 将简历设置为不合适
     * @param toudiId
     */
    public void notSuitable(String toudiId) {
        deliverMapper.notSuitable(toudiId);
    }

    /**
     * 公司预览投递的简历
     * @param resumeId 简历ID
     * @return
     */
    public Resume companyPreviewResume(String resumeId) {
        return resumeMapper.selectById(resumeId);
    }

    /**
     * 公司查看待面试的简历
     * @param userId 用户ID
     * @return
     */
    public List<ManagerToudi> lookInformResume(String userId) {
        List<ManagerToudi> list = new ArrayList<>();
        //查询公司基本信息
        CompanyInfo companyInfo = companyInfoMapper.selectOne(new QueryWrapper<CompanyInfo>().eq("reserved1", userId));
        //根据公司ID查询所有待面试的简历
        List<Deliver> toudis = deliverMapper.selectList(new QueryWrapper<Deliver>().eq("companyid", companyInfo.getId()).eq("deliverState","1"));

        toudis.stream().forEach(s->{
            ManagerToudi toudi = new ManagerToudi();
            //查询投递的简历信息
            Resume resume = resumeMapper.selectById(s.getResumeId());
            //设置简历信息
            toudi.setResume(resume);
            toudi.setDeliver(s);
            list.add(toudi);
        });
        return list;
    }

    /**
     * 公司查看不合适的简历
     * @param userId 用户ID
     * @return
     */
    public List<ManagerToudi> lookNotResume(String userId) {
        List<ManagerToudi> list = new ArrayList<>();
        //查询公司基本信息
        CompanyInfo companyInfo = companyInfoMapper.selectOne(new QueryWrapper<CompanyInfo>().eq("reserved1", userId));
        //根据公司ID查询所有待面试的简历
        List<Deliver> toudis = deliverMapper.selectList(new QueryWrapper<Deliver>().eq("companyid", companyInfo.getId()).eq("deliverState","2"));

        toudis.stream().forEach(s->{
            ManagerToudi toudi = new ManagerToudi();
            //查询投递的简历信息
            Resume resume = resumeMapper.selectById(s.getResumeId());
            //设置简历信息
            toudi.setResume(resume);
            toudi.setDeliver(s);
            list.add(toudi);
        });
        return list;
    }

    /**
     * 公司删除简历
     * @param toudiId 投递简历ID
     */
    public void del(String toudiId) {
        deliverMapper.del(toudiId);
    }
}