package com.zzr.confidant.mapper;

import com.zzr.confidant.model.Resume;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * @description 简历表(Resume)表Mapper接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
public interface ResumeMapper extends BaseMapper<Resume> {

    @Update("update resume set name=#{name},sex=#{sex},maxEducation=#{maxEducation},schoolSystem=#{schoolSystem},phone=#{phone},email=#{email},nowState=#{nowState} where id=#{resumeId}")
    void updateResumeUserInfo(String resumeId, String userId, String name, String sex, String maxEducation, String schoolSystem, String phone, String email, String nowState);

    @Update("update resume set hopeWorkplace=#{hopeWorkplace},jobType=#{jobType},hopePosition=#{hopePosition},hopeSalary=#{hopeSalary} where id=#{resumeId}")
    int saveHopeJob(String resumeId,String userId, String hopeWorkplace, String jobType, String hopePosition, String hopeSalary);

    @Update("update resume set oldCompany=#{oldCompany},oldPosition=#{oldPosition},oldJobStartYear=#{oldJobStartYear},oldJobStartMonth=#{oldJobStartMonth},oldJobEndYear=#{oldJobEndYear},oldJobEndMonth=#{oldJobEndMonth} where id=#{resumeId}")
    int saveResumeOldJob(String resumeId, String userId, String oldCompany, String oldPosition, String oldJobStartYear, String oldJobStartMonth, String oldJobEndYear, String oldJobEndMonth);

    @Update("update resume set projectName=#{projectName},role=#{role},pojStartYear=#{pojStartYear},pojStartMonth=#{pojStartMonth},pojEndYear=#{pojEndYear},pojEndMonth=#{pojEndMonth},pojDescribe=#{pojDescribe} where id=#{resumeId}")
    int saveResumeProject(String resumeId, String userId, String projectName, String role, String pojStartYear, String pojStartMonth, String pojEndYear, String pojEndMonth, String pojDescribe);

    @Update("update resume set school=#{school},education=#{education},majorName=#{majorName},studyStartYear=#{studyStartYear},studyEndYear=#{studyEndYear} where id=#{resumeId}")
    int saveResumeSchool(String resumeId, String userId, String school, String education, String majorName, String studyStartYear, String studyEndYear);

    @Update("update resume set autognosis=#{autognosis} where id=#{resumeId}")
    int saveResumeMyself(String resumeId, String userId, String autognosis);

    @Update("update resume set worksUrl=#{worksUrl},worksDescribe=#{worksDescribe} where id=#{resumeId}")
    int saveResumeWork(String resumeId, String userId, String worksUrl, String worksDescribe);
}