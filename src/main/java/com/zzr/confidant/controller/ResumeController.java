package com.zzr.confidant.controller;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.model.Resume;
import com.zzr.confidant.service.ResumeService;
import com.zzr.confidant.tool.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 赵志然
 * @description 简历表(Resume)表控制层
 * @date 2020-03-05 22:50:36
 */
@Api(tags = "用户简历相关")
@Controller
public class ResumeController {

    //上传地址
    @Value("${file.upload.path}")
    private String filePath;//路径： /Users/zzr/images/

    @Autowired
    private ResumeService resumeService;


    /**
     * 跳转到简历页面
     */
    @ApiOperation(value = "跳转到 简历 页面前查询简历信息", notes = "开发：赵志然")
    @GetMapping("/jianli")
    public String tojianli(@ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userId") String userId,
                           HttpServletRequest request) {
        //调用service层查询当前用户是否有简历信息
        List<Resume> list = resumeService.selectJianli(userId);
        if (list != null && !list.isEmpty()) {
            //查询到了简历信息，则将简历悉尼下存入session中
            HttpSession session = request.getSession();
            session.setAttribute("jianli", list.get(0));
        }
        return "jianli";
    }


   /* @ApiOperation(value = "简历 基本信息", notes = "开发：赵志然")
    @PostMapping("/saveResumeUserInfo")
    public void saveResumeUserInfo(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                                     @ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userIdval") String userId,
                                     @ApiParam(value = "简历姓名") @RequestParam(value = "nameval") String name,
                                     @ApiParam(value = "性别") @RequestParam(value = "sexval") String sex,
                                     @ApiParam(value = "学历") @RequestParam(value = "maxEducationval") String maxEducation,
                                     @ApiParam(value = "工作经验") @RequestParam(value = "schoolSystemval") String schoolSystem,
                                     @ApiParam(value = "电话") @RequestParam(value = "phoneval") String phone,
                                     @ApiParam(value = "邮箱") @RequestParam(value = "emailval") String email,
                                     @ApiParam(value = "目前状态") @RequestParam(value = "nowStateval") String nowState,
                                     @ApiParam(value = "用户头像") MultipartFile userPhoto,
                                     HttpServletRequest request, HttpServletResponse response) {
        // 获取上传文件名
        String filename = userPhoto.getOriginalFilename();
        //生成新文件名
        filename = Tools.getUUID() + filename.substring(filename.lastIndexOf("."));
        //System.out.println("新文件名："+filename);

        // 定义上传文件保存路径 images/
        String path = filePath + "userPhoto/";
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            userPhoto.transferTo(new File(path + File.separator + filename));
            //调用service，将公司基本信息存入数据库
            resumeService.saveOrUpdateUsernfo(resumeId,userId,name,sex,maxEducation,schoolSystem,phone,email,nowState, filename);
            //tojianli(userId,request);
            //调用service层查询当前用户是否有简历信息
            List<Resume> list = resumeService.selectJianli(userId);
            if (list != null && !list.isEmpty()) {
                //查询到了简历信息，则将简历悉尼下存入session中
                HttpSession session = request.getSession();
                session.setAttribute("jianli", list.get(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("jianli?userId="+userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return "jianli";

    }*/

    @ApiOperation(value = "新增过修改 简历 基本信息", notes = "开发：赵志然")
    @PostMapping("/saveResumeUserInfo")
    public void saveOrUpdateResumeUserInfo(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                                   @ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userIdval") String userId,
                                   @ApiParam(value = "简历姓名") @RequestParam(value = "nameval") String name,
                                   @ApiParam(value = "性别") @RequestParam(value = "sexval") String sex,
                                   @ApiParam(value = "学历") @RequestParam(value = "maxEducationval") String maxEducation,
                                   @ApiParam(value = "工作经验") @RequestParam(value = "schoolSystemval") String schoolSystem,
                                   @ApiParam(value = "电话") @RequestParam(value = "phoneval") String phone,
                                   @ApiParam(value = "邮箱") @RequestParam(value = "emailval") String email,
                                   @ApiParam(value = "目前状态") @RequestParam(value = "nowStateval") String nowState,
                                   @ApiParam(value = "用户头像") MultipartFile userPhoto,
                                   HttpServletRequest request, HttpServletResponse response) {
        resumeService.saveOrUpdateUsernfo(resumeId, userId, name, sex, maxEducation, schoolSystem, phone, email, nowState, userPhoto);
        //调用service层查询当前用户是否有简历信息
        List<Resume> list = resumeService.selectJianli(userId);
        if (list != null && !list.isEmpty()) {
            //查询到了简历信息，则将简历信息存入session中
            HttpSession session = request.getSession();
            session.setAttribute("jianli", list.get(0));
        }
        try {
            response.sendRedirect("jianli?userId=" + userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return "jianli";
    }

    @ApiOperation(value = "删除 简历 基本信息，删除整张简历", notes = "开发：赵志然")
    @GetMapping("/delResume")
    public void delResume(@ApiParam(value = "用户ID") @RequestParam(value = "userId") String userId,
                          HttpServletRequest request, HttpServletResponse response){
        resumeService.delResume(userId);
        HttpSession session = request.getSession();
        //清除简历session
        session.removeAttribute("jianli");
        try {
            response.sendRedirect("jianli?userId=" + userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ApiOperation(value = "添加期望工作信息", notes = "开发：赵志然")
    @GetMapping("/saveHopeJob")
    @ResponseBody
    public void saveHopeJob(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                                 @ApiParam(value = "用户ID") @RequestParam(value = "userId") String userId,
                                 @ApiParam(value = "期望工作城市") @RequestParam(value = "hopeWorkplace") String hopeWorkplace,
                                 @ApiParam(value = "工作类型") @RequestParam(value = "jobType") String jobType,
                                 @ApiParam(value = "期望职位") @RequestParam(value = "hopePosition") String hopePosition,
                                 @ApiParam(value = "期望薪资") @RequestParam(value = "hopeSalary") String hopeSalary,
                            HttpServletRequest request, HttpServletResponse response){
        ResultDTO resultDTO = resumeService.saveHopeJob(resumeId,userId,hopeWorkplace,jobType,hopePosition,hopeSalary);
        //调用service层查询当前用户是否有简历信息
        List<Resume> list = resumeService.selectJianli(userId);
            //查询到了简历信息，则将简历信息存入session中
            HttpSession session = request.getSession();
            session.setAttribute("jianli", list.get(0));
        try {
            response.sendRedirect("jianli?userId=" + userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "添加 工作经验信息", notes = "开发：赵志然")
    @PostMapping("/saveResumeOldJob")
    public void saveResumeOldJob(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                            @ApiParam(value = "用户ID") @RequestParam(value = "userId") String userId,
                            @ApiParam(value = "曾工作的公司") @RequestParam(value = "oldCompany") String oldCompany,
                            @ApiParam(value = "曾担任的职位") @RequestParam(value = "oldPosition") String oldPosition,
                            @ApiParam(value = "开始年份") @RequestParam(value = "companyYearStart") String oldJobStartYear,
                            @ApiParam(value = "开始月份") @RequestParam(value = "companyMonthStart") String oldJobStartMonth,
                            @ApiParam(value = "结束年份") @RequestParam(value = "companyYearEnd") String oldJobEndYear,
                            @ApiParam(value = "结束月份") @RequestParam(value = "companyMonthEnd") String oldJobEndMonth,
                            HttpServletRequest request, HttpServletResponse response){
        ResultDTO resultDTO = resumeService.saveResumeOldJob(resumeId,userId,oldCompany,oldPosition,oldJobStartYear,oldJobStartMonth,oldJobEndYear,oldJobEndMonth);
        //调用service层查询当前用户是否有简历信息
        List<Resume> list = resumeService.selectJianli(userId);
        //查询到了简历信息，则将简历信息存入session中
        HttpSession session = request.getSession();
        session.setAttribute("jianli", list.get(0));
        try {
            response.sendRedirect("jianli?userId=" + userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "添加 项目经验", notes = "开发：赵志然")
    @PostMapping("/saveResumeProject")
    public void saveResumeProject(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                                 @ApiParam(value = "用户ID") @RequestParam(value = "userId") String userId,
                                 @ApiParam(value = "项目名称") @RequestParam(value = "pojName") String projectName,
                                 @ApiParam(value = "担任的职位") @RequestParam(value = "role") String role,
                                 @ApiParam(value = "开始年份") @RequestParam(value = "projectYearStart") String pojStartYear,
                                 @ApiParam(value = "开始月份") @RequestParam(value = "projectMonthStart") String pojStartMonth,
                                 @ApiParam(value = "结束年份") @RequestParam(value = "projectYearEnd") String pojEndYear,
                                 @ApiParam(value = "结束月份") @RequestParam(value = "projectMonthEnd") String pojEndMonth,
                                 @ApiParam(value = "项目描述") @RequestParam(value = "pojDescription") String pojDescribe,
                                 HttpServletRequest request, HttpServletResponse response){
        ResultDTO resultDTO = resumeService.saveResumeProject(resumeId,userId,projectName,role,pojStartYear,pojStartMonth,pojEndYear,pojEndMonth,pojDescribe);
        //调用service层查询当前用户是否有简历信息
        List<Resume> list = resumeService.selectJianli(userId);
        //查询到了简历信息，则将简历信息存入session中
        HttpSession session = request.getSession();
        session.setAttribute("jianli", list.get(0));
        try {
            response.sendRedirect("jianli?userId=" + userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "添加 教育经历", notes = "开发：赵志然")
    @PostMapping("/saveResumeSchool")
    public void saveResumeSchool(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                                  @ApiParam(value = "用户ID") @RequestParam(value = "userId") String userId,
                                  @ApiParam(value = "学校名称") @RequestParam(value = "school") String school,
                                  @ApiParam(value = "学历") @RequestParam(value = "degree") String education,
                                  @ApiParam(value = "专业名称") @RequestParam(value = "majorName") String majorName,
                                  @ApiParam(value = "开始年份") @RequestParam(value = "studyStartYear") String studyStartYear,
                                  @ApiParam(value = "结束年份") @RequestParam(value = "schoolYearEnd") String studyEndYear,
                                  HttpServletRequest request, HttpServletResponse response){
        ResultDTO resultDTO = resumeService.saveResumeSchool(resumeId,userId,school,education,majorName,studyStartYear,studyEndYear);
        //调用service层查询当前用户是否有简历信息
        List<Resume> list = resumeService.selectJianli(userId);
        //查询到了简历信息，则将简历信息存入session中
        HttpSession session = request.getSession();
        session.setAttribute("jianli", list.get(0));
        try {
            response.sendRedirect("jianli?userId=" + userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "添加 自我介绍", notes = "开发：赵志然")
    @PostMapping("/saveResumeMyself")
    public void saveResumeMyself(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                                 @ApiParam(value = "用户ID") @RequestParam(value = "userId") String userId,
                                 @ApiParam(value = "自我介绍内容") @RequestParam(value = "autognosis") String autognosis,
                                 HttpServletRequest request, HttpServletResponse response){
        ResultDTO resultDTO = resumeService.saveResumeMyself(resumeId,userId,autognosis);
        //调用service层查询当前用户是否有简历信息
        List<Resume> list = resumeService.selectJianli(userId);
        //查询到了简历信息，则将简历信息存入session中
        HttpSession session = request.getSession();
        session.setAttribute("jianli", list.get(0));
        try {
            response.sendRedirect("jianli?userId=" + userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "添加 作品展示", notes = "开发：赵志然")
    @PostMapping("/saveResumeWork")
    public void saveResumeWork(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                                 @ApiParam(value = "用户ID") @RequestParam(value = "userId") String userId,
                                 @ApiParam(value = "作品展示连接") @RequestParam(value = "worksUrl") String worksUrl,
                                 @ApiParam(value = "作品介绍") @RequestParam(value = "worksDescribe") String worksDescribe,
                                 HttpServletRequest request, HttpServletResponse response){
        ResultDTO resultDTO = resumeService.saveResumeWork(resumeId,userId,worksUrl,worksDescribe);
        //调用service层查询当前用户是否有简历信息
        List<Resume> list = resumeService.selectJianli(userId);
        //查询到了简历信息，则将简历信息存入session中
        HttpSession session = request.getSession();
        session.setAttribute("jianli", list.get(0));
        try {
            response.sendRedirect("jianli?userId=" + userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "预览简历", notes = "开发：赵志然")
    @GetMapping("/previewResume")
    public String previewResume(@ApiParam(value = "简历ID") @RequestParam(value = "resumeId") String resumeId,
                               HttpServletRequest request, HttpServletResponse response){
        //调用service层查询当前用户简历信息
        Resume resume = resumeService.previewResume(resumeId);
        //查询到了简历信息，则将简历信息存入session中
        HttpSession session = request.getSession();
        session.setAttribute("jianli", resume);
        return "preview";
    }



}