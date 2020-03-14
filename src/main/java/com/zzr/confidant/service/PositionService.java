package com.zzr.confidant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.CompanyInfoMapper;
import com.zzr.confidant.mapper.PositionMapper;
import com.zzr.confidant.model.Position;
import com.zzr.confidant.tool.Tools;
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
    CompanyInfoMapper companyInfoMapper;
    @Resource
    PositionMapper positionMapper;

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
}