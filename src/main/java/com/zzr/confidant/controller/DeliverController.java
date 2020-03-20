package com.zzr.confidant.controller;

import com.zzr.confidant.dto.ManagerToudi;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.dto.ToudiDTO;
import com.zzr.confidant.model.Resume;
import com.zzr.confidant.service.DeliverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description 简历投递表(Deliver)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Api(tags = "简历投递相关操作")
@Controller
public class DeliverController {

    @Autowired
    private DeliverService deliverService;


    @ApiOperation(value = "用户投递简历", notes = "开发：赵志然")
    @PostMapping("/toudi/{userId}/{positionId}")
    @ResponseBody
    public ResultDTO toudi(@ApiParam(value = "当前登陆的用户ID") @PathVariable(value = "userId") String userId,
                           @ApiParam(value = "职位ID") @PathVariable(value = "positionId") String positionId,
                           HttpServletRequest request) {
        return deliverService.toudi(userId,positionId);
    }

    @ApiOperation(value = "用户查看已经投递的简历状态", notes = "开发：赵志然")
    @GetMapping("/myToudi")
    public String myToudi(@ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userId") String userId,
                           HttpServletRequest request) {
        List<ToudiDTO> list=deliverService.myToudi(userId);
        HttpSession session = request.getSession();
        session.setAttribute("myToudi",list);
        return "delivery";
    }


    @ApiOperation(value = "用户删除不合适的投递记录", notes = "开发：赵志然")
    @GetMapping("/delToudi")
    public String delToudi(@ApiParam(value = "简历投递ID") @RequestParam(value = "deliverId") String deliverId,
                           @ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userId") String userId,
                          HttpServletRequest request) {
        List<ToudiDTO> list=deliverService.delToudi(deliverId,userId);

        HttpSession session = request.getSession();
        session.setAttribute("myToudi",list);
        return "delivery";
    }


    /**************************************************************************************************************/
    /****************************************************企业用户操作投递记录*****************************************/

    @ApiOperation(value = "公司查看收到的简历", notes = "开发：赵志然")
    @GetMapping("/manager")
    public String delToudi(@ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userId") String userId,
                           HttpServletRequest request) {
        List<ManagerToudi> list = deliverService.managerToudi(userId);
        HttpSession session = request.getSession();
        session.setAttribute("managerToudi",list);
        return "canInterviewResumes";
    }


    @ApiOperation(value = "公司面试", notes = "开发：赵志然")
    @PostMapping("/inform/{toudiId}/{userId}")
    public String inform(@ApiParam(value = "简历投递ID") @PathVariable(value = "toudiId") String toudiId,
                         @ApiParam(value = "当前登陆人ID") @PathVariable(value = "userId") String userId,
                           HttpServletRequest request) {
        //通知面试
        deliverService.inform(toudiId);
        //继续查询未处理的简历信息
        List<ManagerToudi> list = deliverService.managerToudi(userId);
        HttpSession session = request.getSession();
        session.setAttribute("managerToudi",list);
        return "canInterviewResumes";
    }

    @ApiOperation(value = "公司将简历设置为不合适", notes = "开发：赵志然")
    @PostMapping("/notSuitable/{toudiId}/{userId}")
    public String notSuitable(@ApiParam(value = "简历投递ID") @PathVariable(value = "toudiId") String toudiId,
                         @ApiParam(value = "当前登陆人ID") @PathVariable(value = "userId") String userId,
                         HttpServletRequest request) {
        //删除简历
        deliverService.notSuitable(toudiId);
        //继续查询未处理的简历信息
        List<ManagerToudi> list = deliverService.managerToudi(userId);
        HttpSession session = request.getSession();
        session.setAttribute("managerToudi",list);
        return "canInterviewResumes";
    }


    @ApiOperation(value = "公司预览投递的简历", notes = "开发：赵志然")
    @GetMapping("/companyPreviewResume")
    public String companyPreviewResume(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                              HttpServletRequest request) {
        Resume resume = deliverService.companyPreviewResume(resumeId);
        HttpSession session = request.getSession();
        session.setAttribute("companyPreviewResume",resume);
        return "companyPreviewResume";
    }


    @ApiOperation(value = "公司查看待面试的简历", notes = "开发：赵志然")
    @GetMapping("/informResume")
    public String lookInformResume(@ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userId") String userId,
                           HttpServletRequest request) {
        List<ManagerToudi> list = deliverService.lookInformResume(userId);
        HttpSession session = request.getSession();
        session.setAttribute("managerToudi_inform",list);
        return "informResumes";
    }

    @ApiOperation(value = "公司查看不合适的简历", notes = "开发：赵志然")
    @GetMapping("/notResume")
    public String notResume(@ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userId") String userId,
                                   HttpServletRequest request) {
        List<ManagerToudi> list = deliverService.lookNotResume(userId);
        HttpSession session = request.getSession();
        session.setAttribute("managerToudi_not",list);
        return "notResumes";
    }

    @ApiOperation(value = "公司删除简历", notes = "开发：赵志然")
    @PostMapping("/del/{toudiId}/{userId}")
    public String del(@ApiParam(value = "简历投递ID") @PathVariable(value = "toudiId") String toudiId,
                              @ApiParam(value = "当前登陆人ID") @PathVariable(value = "userId") String userId,
                              HttpServletRequest request) {
        //删除简历
        deliverService.del(toudiId);
        return "notResumes";
    }

}