package com.zzr.confidant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzr.confidant.dto.PositionItem;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.dto.SelectJobItemDTO;
import com.zzr.confidant.dto.UserLookJob;
import com.zzr.confidant.mapper.*;
import com.zzr.confidant.model.*;
import com.zzr.confidant.tool.Tools;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description 职位表(Position)表服务接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Service
public class PositionService{

    @Resource
    PositionMapper positionMapper;

    @Resource
    CompanyInfoMapper companyInfoMapper;
    @Resource
    CompanyTagsMapper companyTagsMapper;
    @Resource
    CompanyInitMapper companyInitMapper;
    @Resource
    CompanyProductMapper companyProductMapper;
    @Resource
    DeliverMapper deliverMapper;


    /**
     * 查询职位展示所需的所有信息
     * @param companyId 公司ID
     * @param userId 当前登陆人ID
     * @param positionType 职位类别
     * @param positionName 以为名称
     * @param department 所属部门
     * @param jobType 工作性质
     * @param leastSalary 最低月薪
     * @param mostSalary 最高月薪
     * @param workCity 工作城市
     * @param workSuffer 工作经验
     * @param education 学历要求
     * @param positionTempt 职位诱惑
     * @param positionDescribe 职位描述
     * @param contactWay 联系邮箱
     */
    public ResultDTO positionInfo(String companyId, String userId, String positionType, String positionName,
                                  String department, String jobType, String leastSalary, String mostSalary,
                                  String workCity, String workSuffer, String education, String positionTempt,
                                  String positionDescribe, String contactWay) {
        ResultDTO resultDTO = new ResultDTO();
        Position position = new Position();
        position.setCompanyId(companyId);
        //根据公司id查询公司名称
        String companyName = companyInfoMapper.selectById(companyId).getCompanyName();
        position.setCompanyName(companyName);
        position.setPositionType(positionType);
        position.setPositionName(positionName);
        position.setDepartment(department);
        position.setJobType(jobType);
        position.setLeastSalary(Integer.parseInt(leastSalary));
        position.setMostSalary(Integer.parseInt(mostSalary));
        position.setWorkCity(workCity);
        position.setWorkSuffer(workSuffer);
        position.setEducation(education);
        position.setPositionTempt(positionTempt);
        position.setPositionDescribe(positionDescribe);
        position.setContactWay(contactWay);
        //获取当前事件作为发布时间
        String time = Tools.dateToStr("yyyy-MM-dd HH:mm", new Date());
        position.setPublishTime(time);

        resultDTO.setCode(0);
        resultDTO.setMsg("预览数据封装完毕");
        resultDTO.setData(position);
        return resultDTO;
    }

    /**
     * 创建职位
     * @param companyId 公司ID
     * @param userId 当前登陆人ID
     * @param positionType 职位类别
     * @param positionName 以为名称
     * @param department 所属部门
     * @param jobType 工作性质
     * @param leastSalary 最低月薪
     * @param mostSalary 最高月薪
     * @param workCity 工作城市
     * @param workSuffer 工作经验
     * @param education 学历要求
     * @param positionTempt 职位诱惑
     * @param positionDescribe 职位描述
     * @param contactWay 联系邮箱
     */
    public ResultDTO publishJob(String companyId, String userId, String positionType, String positionName,
                                String department, String jobType, String leastSalary, String mostSalary,
                                String workCity, String workSuffer, String education, String positionTempt,
                                String positionDescribe, String contactWay) {
        ResultDTO resultDTO = new ResultDTO();
        Position position = new Position();
        position.setId(Tools.getUUID());
        position.setCompanyId(companyId);
        //根据公司id查询公司名称
        String companyName = companyInfoMapper.selectById(companyId).getCompanyName();
        position.setCompanyName(companyName);
        position.setPositionType(positionType);
        position.setPositionName(positionName);
        position.setDepartment(department);
        position.setJobType(jobType);
        position.setLeastSalary(Integer.parseInt(leastSalary));
        position.setMostSalary(Integer.parseInt(mostSalary));
        position.setWorkCity(workCity);
        position.setWorkSuffer(workSuffer);
        position.setEducation(education);
        position.setPositionTempt(positionTempt);
        position.setPositionDescribe(positionDescribe);
        position.setContactWay(contactWay);
        //获取当前事件作为发布时间
        String time = Tools.dateToStr("yyyy-MM-dd HH:mm", new Date());
        position.setPublishTime(time);
        //设置职位状态，默认新创建的为有效状态0
        position.setPositionState("0");
        //调用mapper层
        int i = positionMapper.insert(position);
        if(i==1){
            //成功
            resultDTO.setCode(0);
            resultDTO.setMsg("职位创建成功");
            resultDTO.setData(position);
        }else{
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("职位创建失败");
            resultDTO.setData(position);
        }
        return resultDTO;
    }

    /**
     * 查询所有有效职位
     * @param companyId 公司ID
     * @return
     */
    public ResultDTO selectEffectivePosition(String companyId) {
        List<Position> list=new ArrayList<>();
        ResultDTO result=new ResultDTO();
        QueryWrapper<Position> eq = new QueryWrapper<Position>().eq("companyId", companyId).eq("positionState", "0");
        list = positionMapper.selectList(eq);
        result.setCode(0);
        result.setMsg("查询成功");
        result.setData(list);
        return result;
    }

    /**
     * 下线职位
     * @param positionId 职位ID
     * @return
     */
    public ResultDTO offline(String positionId) {
        ResultDTO result=new ResultDTO();
        //调用mapper层，修改职位状态
        int i = positionMapper.offline(positionId);
        if(i==1){
            //成功
            //下线成功则将简历投递表中的相关记录设置为已删除状态 2
            deliverMapper.delState(positionId);

            result.setCode(0);
            result.setMsg("下线成功");
            result.setData(null);
        }else{
            //失败
            result.setCode(1);
            result.setMsg("下线失败");
            result.setData(null);
        }
        return result;
    }

    /**
     * 删除职位
     * @param positionId 职位ID
     * @return
     */
    public ResultDTO delPosition(String positionId) {
        ResultDTO result=new ResultDTO();
        //调用mapper层，修改职位状态
        int i = positionMapper.delPosition(positionId);
        if(i==1){
            //成功
            //删除成功则将简历投递表中的相关记录设置为已删除状态 2
            deliverMapper.delState(positionId);
            result.setCode(0);
            result.setMsg("删除成功");
            result.setData(null);
        }else{
            //失败
            result.setCode(1);
            result.setMsg("删除失败");
            result.setData(null);
        }
        return result;
    }

    /**
     * 查询所有已经下线的职位
     * @param companyId 公司ID
     * @return
     */
    public ResultDTO selectOfflinePosition(String companyId) {
        List<Position> list=new ArrayList<>();
        ResultDTO result=new ResultDTO();
        QueryWrapper<Position> eq = new QueryWrapper<Position>().eq("companyId", companyId).eq("positionState", "1");
        list = positionMapper.selectList(eq);
        result.setCode(0);
        result.setMsg("查询成功");
        result.setData(list);
        return result;
    }

    /**
     * 重新上线职位
     * @param positionId 职位ID
     * @return
     */
    public ResultDTO reOnline(String positionId) {
        ResultDTO result=new ResultDTO();
        //获取当前事件，修改上线时间
        String time=Tools.dateToStr("yyyy-MM-dd HH:mm",new Date());
        //调用mapper层，修改职位状态
        int i = positionMapper.reOnline(positionId,time);
        if(i==1){
            //成功
            result.setCode(0);
            result.setMsg("上线成功");
            result.setData(null);
        }else{
            //失败
            result.setCode(1);
            result.setMsg("上线失败");
            result.setData(null);
        }
        return result;
    }

    /**
     * 用户查看公司发布的职位信息
     * @param positionId 职位ID
     * @return
     */
    public UserLookJob userLookJob(String positionId) {
        UserLookJob lookJob = new UserLookJob();
        //查询职位表
        Position position=positionMapper.selectById(positionId);
        //根据职位表中 公司ID查询公司信息
        CompanyInfo companyInfo=companyInfoMapper.selectById(position.getCompanyId());
        //查询标签
        CompanyTags companyTags=companyTagsMapper.selectById(position.getCompanyId());
        //查询创始人list
        List<CompanyInit> founderList=companyInitMapper.selectList(new QueryWrapper<CompanyInit>().eq("companyId",position.getCompanyId()));
        //查询产品
        CompanyProduct companyProduct=companyProductMapper.selectById(position.getCompanyId());

        lookJob.setPosition(position);
        lookJob.setCompanyInfo(companyInfo);
        lookJob.setCompanyTags(companyTags);
        lookJob.setCompanyInitList(founderList);
        lookJob.setCompanyProduct(companyProduct);
        return lookJob;
    }

    /**
     * 用户根据职位名称搜索职位信息，展示到list页面
     * @param positionName 职位名称
     * @param page 第几页
     * @param size 每页条数
     * @return
     */
    public SelectJobItemDTO selectJobByName(String positionName, String page, String size) {
        SelectJobItemDTO jobItemDTO = new SelectJobItemDTO();
        QueryWrapper<Position> queryWrapper = new QueryWrapper<Position>();
        queryWrapper.like("positionName",positionName).ne("positionState","1").ne("positionState","2");
        //分页查询
        Page<Position> positionPage = new Page<Position>(Integer.parseInt(page),Integer.parseInt(size));
        IPage<Position> iPage = positionMapper.selectPage(positionPage,queryWrapper);
        Long pageCount=iPage.getPages();//总页数
        Long total=iPage.getTotal();//总记录数
        List<Position> positionList = iPage.getRecords();//查询出来的数据
        List<PositionItem> positionItemList=new ArrayList<>();

        positionList.stream().forEach(job->{
            //获取公司ID
            String companyId=job.getCompanyId();
            //遍历查询出来的数据，将数据封装成为positionItem
            PositionItem positionItem = new PositionItem();
            positionItem.setPosition(job);
            positionItem.setCompanyInfo(companyInfoMapper.selectById(job.getCompanyId()));
            positionItem.setCompanyTags(companyTagsMapper.selectOne(new QueryWrapper<CompanyTags>().eq("companyId",companyId)));
            positionItem.setCompanyInitList(companyInitMapper.selectList(new QueryWrapper<CompanyInit>().eq("companyId",companyId)));
            positionItem.setCompanyProduct(companyProductMapper.selectOne(new QueryWrapper<CompanyProduct>().eq("companyId",companyId)));
            positionItemList.add(positionItem);
        });
        jobItemDTO.setPositionItemList(positionItemList);
        jobItemDTO.setPositionName(positionName);
        jobItemDTO.setPage(Long.parseLong(page));//设置当前页数
        jobItemDTO.setPageCount(pageCount);//设置总页数
        jobItemDTO.setTotal(total);//设置总记录数

        return jobItemDTO;
    }

    /**
     * list页面条件查询
     * @param page 页数
     * @param size 每夜条数
     * @param positionName 职位名称
     * @param salary 工资范围
     * @param suffer 工作经验
     * @param education 学历
     * @param jobType 工作性质
     * @return
     */
    public SelectJobItemDTO select(String page, String size, String positionName, String salary, String suffer, String education, String jobType) {
        SelectJobItemDTO jobItemDTO = new SelectJobItemDTO();
        List<PositionItem> positionItemList=new ArrayList<>();
        //获取最低工资、最高工资
        Integer maxSalary=0;//最高工资
        Integer minSalary=0;//最低工资
        if("2K以下".equals(salary)){maxSalary=2;minSalary=0;}
        if("2K-5K".equals(salary)){maxSalary=5;minSalary=2;}
        if("5K-10K".equals(salary)){maxSalary=10;minSalary=5;}
        if("10K-15K".equals(salary)){maxSalary=15;minSalary=10;}
        if("15K-25K".equals(salary)){maxSalary=25;minSalary=15;}
        if("25K-50K".equals(salary)){maxSalary=50;minSalary=25;}
        if("50K以上".equals(salary)){maxSalary=0;minSalary=50;}

        //ge 大于等于 le 小于等于
        QueryWrapper<Position> queryWrapper = new QueryWrapper<Position>();

        Integer finalMinSalary = minSalary;
        Integer finalMaxSalary = maxSalary;
        System.out.println("max:"+finalMaxSalary);
        System.out.println("min:"+finalMinSalary);
        boolean boo=true;
        //如果未选择薪资范围则不拼接sql
        if(finalMaxSalary==finalMaxSalary){if(finalMaxSalary==0){boo=false;}}

        queryWrapper.like("positionName",positionName)
                .ne("positionState","1")
                .ne("positionState","2")
                .le(finalMinSalary !=0,"leastSalary", finalMinSalary)
                .ge(finalMaxSalary !=0,"mostSalary", finalMaxSalary)

                .eq(StringUtils.isNotEmpty(suffer),"workSuffer",suffer)
                .eq(StringUtils.isNotEmpty(education),"education",education)
                .eq(StringUtils.isNotEmpty(jobType),"jobType",jobType);

        //分页查询
        Page<Position> positionPage = new Page<Position>(Integer.parseInt(page),Integer.parseInt(size));
        IPage<Position> iPage = positionMapper.selectPage(positionPage,queryWrapper);
        Long pageCount=iPage.getPages();//总页数
        Long total=iPage.getTotal();//总记录数
        List<Position> positionList = iPage.getRecords();//查询出来的数据
        positionList.stream().forEach(job->{
            //获取公司ID
            String companyId=job.getCompanyId();
            //遍历查询出来的数据，将数据封装成为positionItem
            PositionItem positionItem = new PositionItem();
            positionItem.setPosition(job);
            positionItem.setCompanyInfo(companyInfoMapper.selectById(job.getCompanyId()));
            positionItem.setCompanyTags(companyTagsMapper.selectOne(new QueryWrapper<CompanyTags>().eq("companyId",companyId)));
            positionItem.setCompanyInitList(companyInitMapper.selectList(new QueryWrapper<CompanyInit>().eq("companyId",companyId)));
            positionItem.setCompanyProduct(companyProductMapper.selectOne(new QueryWrapper<CompanyProduct>().eq("companyId",companyId)));
            positionItemList.add(positionItem);
        });
        jobItemDTO.setPositionItemList(positionItemList); //设置职位公司信息 集合
        jobItemDTO.setPositionName(positionName); //设置职位名称
        jobItemDTO.setPage(Long.parseLong(page));//设置当前页数
        jobItemDTO.setPageCount(pageCount);//设置总页数
        jobItemDTO.setTotal(total);//设置总记录数
        jobItemDTO.setSalary(salary);//设置薪资范围
        jobItemDTO.setSuffer(suffer);//设置工作经验
        jobItemDTO.setEducation(education);//设置学历
        jobItemDTO.setJobType(jobType);//设置工作性质

        return jobItemDTO;
    }
}