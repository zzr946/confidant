package com.zzr.confidant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.ResumeMapper;
import com.zzr.confidant.model.Resume;
import com.zzr.confidant.tool.Tools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @description 简历表(Resume)表服务接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Service
public class ResumeService {
    //上传地址
    @Value("${file.upload.path}")
    private String filePath;//路径： /Users/zzr/images/

    @Resource
    ResumeMapper resumeMapper;

    /**
     * 查询用户的简历信息
     * @param userId 用户ID
     * @return
     */
    public List<Resume> selectJianli(String userId) {
        return resumeMapper.selectList(new QueryWrapper<Resume>().eq("userId", userId));
    }

    /**
     * 保存或修改 简历 用户基本信息
     * @param userId 用户ID
     * @param name 姓名
     * @param sex 性别
     * @param maxEducation 最高学历
     * @param schoolSystem 工作经验
     * @param phone 手机号码
     * @param email 邮箱
     * @param nowState 目前状态
     * @param userPhoto 头像文件对象
     * @return
     */
//    public void saveOrUpdateUsernfo(String resumeId, String userId, String name, String sex, String maxEducation, String schoolSystem,
//                                    String phone, String email, String nowState, String filename) {
//        ResultDTO resultDTO = new ResultDTO();
//        if(resumeId==null||resumeId==""||"".equals(resumeId)) {
//            //简历ID不存在，新增简历
//            Resume resume = new Resume();
//            resume.setId(Tools.getUUID());
//            resume.setUserId(userId);
//            resume.setName(name);
//            resume.setSex(sex);
//            resume.setMaxEducation(maxEducation);
//            resume.setSchoolSystem(schoolSystem);
//            resume.setPhone(phone);
//            resume.setEmail(email);
//            resume.setNowState(nowState);
//            resume.setPhoto(filename);
//            //调用mappe层
//            int i = resumeMapper.insert(resume);
//            //插入完成后，查询当前用户的简历
//            //Resume resume1=resumeMapper.selectOne(new QueryWrapper<Resume>().eq("userId",userId));
////            resultDTO.setCode(0);
////            resultDTO.setMsg("新增完成");
////            resultDTO.setData(null);
//        }else{
//            //简历ID存在，修改简历
//            resumeMapper.updateResumeUserInfo(resumeId,userId,name,sex,maxEducation,schoolSystem,phone,email,nowState,filename);
//        }
//        //return resultDTO;
//    }
    public void saveOrUpdateUsernfo(String resumeId, String userId, String name, String sex, String maxEducation, String schoolSystem,
                                    String phone, String email, String nowState, MultipartFile userPhoto) {
        ResultDTO resultDTO = new ResultDTO();
        if(resumeId==null||resumeId==""||"".equals(resumeId)) {
            //简历ID不存在，新增简历
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            Resume resume = new Resume();
            resume.setId(Tools.getUUID());
            resume.setUserId(userId);
            resume.setName(name);
            resume.setSex(sex);
            resume.setMaxEducation(maxEducation);
            resume.setSchoolSystem(schoolSystem);
            resume.setPhone(phone);
            resume.setEmail(email);
            resume.setNowState(nowState);
            resume.setPhoto(filename);
            resume.setResumeState("0");
            //调用mappe层
            int i = resumeMapper.insert(resume);
            //插入完成后，查询当前用户的简历
            //Resume resume1=resumeMapper.selectOne(new QueryWrapper<Resume>().eq("userId",userId));
//            resultDTO.setCode(0);
//            resultDTO.setMsg("新增完成");
//            resultDTO.setData(null);
        }else{
            //简历ID存在，修改简历
            resumeMapper.updateResumeUserInfo(resumeId,userId,name,sex,maxEducation,schoolSystem,phone,email,nowState);
        }
        //return resultDTO;
    }


    /**
     * 根据用户ID删除简历
     * @param userId 用户ID
     */
    public void delResume(String userId) {
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        resumeMapper.delete(queryWrapper);
    }

    /**
     * 新增期望工作信息
     * @param userId 用户ID
     * @param hopeWorkplace 期望工作城市
     * @param jobType 工作类型
     * @param hopePosition 期望职位
     * @param hopeSalary 期望薪资
     * @return
     */
    public ResultDTO saveHopeJob(String resumeId,String userId, String hopeWorkplace, String jobType, String hopePosition, String hopeSalary) {
        ResultDTO resultDTO=new ResultDTO();
        int i = resumeMapper.saveHopeJob(resumeId,userId,hopeWorkplace,jobType,hopePosition,hopeSalary);
        if(i==1){
            //成功
            resultDTO.setCode(0);
            resultDTO.setMsg("成功");
            resultDTO.setData(null);
        }else{
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     *  新增工作经历
     * @param resumeId 简历ID
     * @param userId 用户ID
     * @param oldCompany 曾工作公司
     * @param oldPosition 曾担任职位
     * @param oldJobStartYear 开始年份
     * @param oldJobStartMonth 开始月份
     * @param oldJobEndYear 结束年份
     * @param oldJobEndMonth 结束月份
     * @return
     */
    public ResultDTO saveResumeOldJob(String resumeId, String userId, String oldCompany, String oldPosition, String oldJobStartYear, String oldJobStartMonth, String oldJobEndYear, String oldJobEndMonth) {
        ResultDTO resultDTO=new ResultDTO();
        int i = resumeMapper.saveResumeOldJob(resumeId,userId,oldCompany,oldPosition,oldJobStartYear,oldJobStartMonth,oldJobEndYear,oldJobEndMonth);
        if(i==1){
            //成功
            resultDTO.setCode(0);
            resultDTO.setMsg("成功");
            resultDTO.setData(null);
        }else{
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 新增项目经验
     * @param resumeId 简历ID
     * @param userId 用户ID
     * @param projectName 项目名称
     * @param role 担任职务
     * @param pojStartYear 开始年份
     * @param pojStartMonth 开始月份
     * @param pojEndYear 结束年份
     * @param pojEndMonth 结束月份
     * @param pojDescribe 项目描述
     * @return
     */
    public ResultDTO saveResumeProject(String resumeId, String userId, String projectName, String role, String pojStartYear, String pojStartMonth, String pojEndYear, String pojEndMonth, String pojDescribe) {
        ResultDTO resultDTO=new ResultDTO();
        int i = resumeMapper.saveResumeProject(resumeId,userId,projectName,role,pojStartYear,pojStartMonth,pojEndYear,pojEndMonth,pojDescribe);
        if(i==1){
            //成功
            resultDTO.setCode(0);
            resultDTO.setMsg("成功");
            resultDTO.setData(null);
        }else{
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 新增教育经历
     * @param resumeId 简历ID
     * @param userId 用户ID
     * @param school 学校名称
     * @param education 学历
     * @param majorName 专业名称
     * @param studyStartYear 开始年份
     * @param studyEndYear 结束年份
     * @return
     */
    public ResultDTO saveResumeSchool(String resumeId, String userId, String school, String education, String majorName, String studyStartYear, String studyEndYear) {
        ResultDTO resultDTO=new ResultDTO();
        int i = resumeMapper.saveResumeSchool(resumeId,userId,school,education,majorName,studyStartYear,studyEndYear);
        if(i==1){
            //成功
            resultDTO.setCode(0);
            resultDTO.setMsg("成功");
            resultDTO.setData(null);
        }else{
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 自我介绍
     * @param resumeId 简历ID
     * @param userId 用户ID
     * @param autognosis 自我介绍内容
     * @return
     */
    public ResultDTO saveResumeMyself(String resumeId, String userId, String autognosis) {
        ResultDTO resultDTO=new ResultDTO();
        int i = resumeMapper.saveResumeMyself(resumeId,userId,autognosis);
        if(i==1){
            //成功
            resultDTO.setCode(0);
            resultDTO.setMsg("成功");
            resultDTO.setData(null);
        }else{
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 作品展示
     * @param resumeId 简历ID
     * @param userId 用户ID
     * @param worksUrl 作品展示连接
     * @param worksDescribe 作品介绍
     * @return
     */
    public ResultDTO saveResumeWork(String resumeId, String userId, String worksUrl, String worksDescribe) {
        ResultDTO resultDTO=new ResultDTO();
        int i = resumeMapper.saveResumeWork(resumeId,userId,worksUrl,worksDescribe);
        if(i==1){
            //成功
            resultDTO.setCode(0);
            resultDTO.setMsg("成功");
            resultDTO.setData(null);
        }else{
            //失败
            resultDTO.setCode(1);
            resultDTO.setMsg("失败");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    /**
     * 预览简历 根据ID查询简历信息
     * @param resumeId 简历ID
     * @return
     */
    public Resume previewResume(String resumeId) {
        return resumeMapper.selectById(resumeId);
    }
}