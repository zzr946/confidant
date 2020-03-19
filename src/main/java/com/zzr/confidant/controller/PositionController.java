package com.zzr.confidant.controller;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.dto.SelectJobItemDTO;
import com.zzr.confidant.dto.UserLookJob;
import com.zzr.confidant.mapper.CompanyInfoMapper;
import com.zzr.confidant.model.Position;
import com.zzr.confidant.service.PositionService;
import com.zzr.confidant.tool.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.util.Date;

/**
 * @author 赵志然
 * @description 职位表(Position)表控制层
 * @date 2020-03-05 22:50:36
 */
@Api(tags = "相关职位操作")
@Controller
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Resource
    CompanyInfoMapper companyInfoMapper;

    @ApiOperation(value = "前往创建职位页面", notes = "开发：赵志然")
    @GetMapping("/create")
    public String createJob() {
        return "create";
    }

    @ApiOperation(value = "前往预览职位页面", notes = "开发：赵志然")
    @GetMapping("/previewJob")
    public String previewJob() {
        return "previewJob";
    }

    @ApiOperation(value = "前往预览职位页面,封装数据", notes = "开发：赵志然")
    @PostMapping("/previewJob")
    @ResponseBody
    public ResultDTO previewJob(@ApiParam(value = "公司Id") @RequestParam("companyId") String companyId,
                                @ApiParam(value = "当前登陆人Id") @RequestParam("userId") String userId,
                                @ApiParam(value = "职位类别") @RequestParam("positionType") String positionType,
                                @ApiParam(value = "职位名称") @RequestParam("positionName") String positionName,
                                @ApiParam(value = "所属部门") @RequestParam("department") String department,
                                @ApiParam(value = "工作性质") @RequestParam("jobType") String jobType,
                                @ApiParam(value = "最低月薪") @RequestParam("leastSalary") String leastSalary,
                                @ApiParam(value = "最高月薪") @RequestParam("mostSalary") String mostSalary,
                                @ApiParam(value = "工作城市") @RequestParam("workCity") String workCity,
                                @ApiParam(value = "工作经验") @RequestParam("workSuffer") String workSuffer,
                                @ApiParam(value = "学历要求") @RequestParam("education") String education,
                                @ApiParam(value = "职位诱惑") @RequestParam("positionTempt") String positionTempt,
                                @ApiParam(value = "职位描述") @RequestParam("positionDescribe") String positionDescribe,
                                @ApiParam(value = "联系方式") @RequestParam("contactWay") String contactWay,
                                HttpServletRequest request) {
        //获取session
        HttpSession session = request.getSession();
        //调用service层，将职位展示所需要的所有信息查询出来
        ResultDTO result = positionService.positionInfo(companyId, userId, positionType, positionName, department, jobType, leastSalary,
                mostSalary, workCity, workSuffer, education, positionTempt, positionDescribe, contactWay);
        session.setAttribute("previewJob", result.getData());
        return result;
    }


    @ApiOperation(value = "前往预览职位页面,封装数据", notes = "开发：赵志然")
    @PostMapping("/publishJob")
    @ResponseBody
    public ResultDTO publishJob(@ApiParam(value = "公司Id") @RequestParam("companyId") String companyId,
                                @ApiParam(value = "当前登陆人Id") @RequestParam("userId") String userId,
                                @ApiParam(value = "职位类别") @RequestParam("positionType") String positionType,
                                @ApiParam(value = "职位名称") @RequestParam("positionName") String positionName,
                                @ApiParam(value = "所属部门") @RequestParam("department") String department,
                                @ApiParam(value = "工作性质") @RequestParam("jobType") String jobType,
                                @ApiParam(value = "最低月薪") @RequestParam("leastSalary") String leastSalary,
                                @ApiParam(value = "最高月薪") @RequestParam("mostSalary") String mostSalary,
                                @ApiParam(value = "工作城市") @RequestParam("workCity") String workCity,
                                @ApiParam(value = "工作经验") @RequestParam("workSuffer") String workSuffer,
                                @ApiParam(value = "学历要求") @RequestParam("education") String education,
                                @ApiParam(value = "职位诱惑") @RequestParam("positionTempt") String positionTempt,
                                @ApiParam(value = "职位描述") @RequestParam("positionDescribe") String positionDescribe,
                                @ApiParam(value = "联系方式") @RequestParam("contactWay") String contactWay) {
        //调用service层将职位信息存储起来
        return positionService.publishJob(companyId, userId, positionType, positionName, department, jobType, leastSalary,
                mostSalary, workCity, workSuffer, education, positionTempt, positionDescribe, contactWay);
    }

    @ApiOperation(value = "前往有效职位页面", notes = "开发：赵志然")
    @GetMapping("/effective")
    public String effective(@ApiParam(value = "公司Id") @RequestParam("companyId") String companyId,
                            HttpServletRequest request) {
//        System.out.println("前往有效职位页面："+companyId);
        //获取session
        HttpSession session = request.getSession();
        //调用mapper层查询所有有效职位
        ResultDTO result = positionService.selectEffectivePosition(companyId);
        session.setAttribute("effectivePositions", result.getData());
        return "positions";
    }


    @ApiOperation(value = "下线职位", notes = "开发：赵志然")
    @PostMapping("/offline/{positionId}")
    @ResponseBody
    public ResultDTO offline(@ApiParam(value = "positionId") @PathVariable("positionId") String positionId,
                             HttpServletRequest request) {
        //调用mapper层,下线当前职位
        return positionService.offline(positionId);
    }

    @ApiOperation(value = "删除职位", notes = "开发：赵志然")
    @PostMapping("/del/{positionId}")
    @ResponseBody
    public ResultDTO delPosition(@ApiParam(value = "positionId") @PathVariable("positionId") String positionId,
                                 HttpServletRequest request) {
        //调用mapper层,删除当前职位
        return positionService.delPosition(positionId);
    }


    @ApiOperation(value = "前往已经下线职位页面", notes = "开发：赵志然")
    @GetMapping("/invalid")
    public String invalid(@ApiParam(value = "公司Id") @RequestParam("companyId") String companyId,
                          HttpServletRequest request) {
        //获取session
        HttpSession session = request.getSession();
        //调用mapper层查询所有有效职位
        ResultDTO result = positionService.selectOfflinePosition(companyId);
        session.setAttribute("offlinePositions", result.getData());
        return "offlinePositions";
    }


    @ApiOperation(value = "重新上线职位", notes = "开发：赵志然")
    @PostMapping("/reOnline/{positionId}")
    @ResponseBody
    public ResultDTO reOnline(@ApiParam(value = "positionId") @PathVariable("positionId") String positionId,
                              HttpServletRequest request) {
        //调用mapper层,重新上线当前职位
        return positionService.reOnline(positionId);
    }

    /********************************************************************************************************/
    /*********************************************用户模块*****************************************************/

    @ApiOperation(value = "用户点击查看职位详细信息", notes = "开发：赵志然")
    @GetMapping("/jobs")
    public String jobs(@ApiParam(value = "职位ID") @RequestParam("positionId") String positionId,
                       HttpServletRequest request) {
        UserLookJob lookJob = positionService.userLookJob(positionId);
        HttpSession session = request.getSession();
        session.setAttribute("UserLookPosition", lookJob);
        return "jobdetail";
    }


    @ApiOperation(value = "用户根据职位名称搜索", notes = "开发：赵志然")
    @GetMapping("/list")
    public String list(@ApiParam(value = "职位名称") @RequestParam("position") String positionName,
                       @ApiParam(value = "页数") @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                       @ApiParam(value = "每页条数") @RequestParam(name = "size", required = false, defaultValue = "10") String size,
                       HttpServletRequest request) {
        SelectJobItemDTO jobItemDTO = positionService.selectJobByName(positionName, page, size);
        HttpSession session = request.getSession();
        session.setAttribute("jobItem", jobItemDTO);
        return "list";
    }

    @ApiOperation(value = "用户根据职位名称搜索", notes = "开发：赵志然")
    @GetMapping("/listSelect")
    public String select(@ApiParam(value = "页数") @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                         @ApiParam(value = "每页条数") @RequestParam(name = "size", required = false, defaultValue = "10") String size,
                         @ApiParam(value = "职位名称") @RequestParam("position") String positionName,
                         @ApiParam(value = "薪资范围") @RequestParam("salary") String salary,
                         @ApiParam(value = "工作经验") @RequestParam("suffer") String suffer,
                         @ApiParam(value = "学历") @RequestParam("education") String education,
                         @ApiParam(value = "工作性质") @RequestParam("jobType") String jobType,
                         HttpServletRequest request) {
        if(positionName.contains(",")){positionName=positionName.substring(0,positionName.indexOf(','));}
        if(salary.contains(",")){salary=salary.substring(0,salary.indexOf(','));}
        if(suffer.contains(",")){suffer=suffer.substring(0,suffer.indexOf(','));}
        if(education.contains(",")){education=education.substring(0,education.indexOf(','));}
        if(jobType.contains(",")){jobType=jobType.substring(0,jobType.indexOf(','));}

        //System.out.println("后端接受到的职位名称："+positionName);
        //System.out.println("后端接受到的gongzifanwei:："+salary);
        SelectJobItemDTO jobItemDTO = positionService.select(page, size,positionName,salary,suffer,education,jobType);
        HttpSession session = request.getSession();
        session.setAttribute("jobItem", jobItemDTO);
        return "list";
    }


}