package com.zzr.confidant.controller;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.service.CompanyInitService;
import com.zzr.confidant.tool.Tools;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author 赵志然
 * @description 公司创始人表(Companyinit)表控制层
 * @date 2020-03-05 22:50:36
 */
@Controller
public class CompanyInitController {

    @Autowired
    private CompanyInitService companyinitService;

    //上传地址
    @Value("${file.upload.path}")
    private String filePath;//路径： /Users/zzr/images/


    /**
     * 添加公司创始人
     * @param companyId 公司id
     * @param count 添加的人数
     * @param founderPhoto0 第一个创始人头像
     * @param founderName0 第一个创始人姓名
     * @param founderPosition0 第一个创始人职位
     * @param founderDescribe0 第一个创始人简介
     * @param founderPhoto1 第二个创始人头像
     * @param founderName1 第二个创始人姓名
     * @param founderPosition1 第二个创始人职位
     * @param founderDescribe1 第二个创始人简介
     * @param founderPhoto2 第三个创始人头像
     * @param founderName2 第三个创始人姓名
     * @param founderPosition2 第三个创始人职位
     * @param founderDescribe2 第三个创始人简介
     * @return
     */
    @ApiOperation(value = "公司创始人头像上传", notes = "开发：赵志然")
    @PostMapping("/saveFounder")
    @Transactional
    public void uploadFounder(@ApiParam(value = "公司id") String companyId,
                                @ApiParam(value = "添加的创始人人数") String count,
                                @ApiParam(value = "创始人头像0") MultipartFile founderPhoto0,
                                @ApiParam(value = "创始人名字0") String founderName0,
                                @ApiParam(value = "创始人职位0") String founderPosition0,
                                @ApiParam(value = "创始人介绍0") String founderDescribe0,
                                @ApiParam(value = "创始人头像1") MultipartFile founderPhoto1,
                                @ApiParam(value = "创始人名字1") String founderName1,
                                @ApiParam(value = "创始人职位1") String founderPosition1,
                                @ApiParam(value = "创始人介绍1") String founderDescribe1,
                                @ApiParam(value = "创始人头像2") MultipartFile founderPhoto2,
                                @ApiParam(value = "创始人名字2") String founderName2,
                                @ApiParam(value = "创始人职位2") String founderPosition2,
                                @ApiParam(value = "创始人介绍2") String founderDescribe2,
                                HttpServletResponse response) {
//        System.out.println("公司id:"+companyId);
//        System.out.println("添加的创始人人数:"+count);
//
//        System.out.println("创始人头像0："+founderPhoto0);
//        System.out.println("创始人名字0："+founderName0);
//        System.out.println("创始人职位0："+founderPosition0);
//        System.out.println("创始人介绍0："+remark0);
//
//        System.out.println("创始人头像1："+founderPhoto1);
//        System.out.println("创始人名字1："+founderName1);
//        System.out.println("创始人职位1："+founderPosition1);
//        System.out.println("创始人介绍1："+foundDescribe1);
//
//        System.out.println("创始人头像2："+founderPhoto2);
//        System.out.println("创始人名字2："+founderName2);
//        System.out.println("创始人职位2："+founderPosition2);
//        System.out.println("创始人介绍2："+foundDescribe2);

        // 定义上传文件保存路径 images/founderPhoto/
        String path = filePath + "founderPhoto/";
        // 获取上传文件名
        String filename0 = founderPhoto0.getOriginalFilename();
        //生成新文件名
        filename0 = Tools.getUUID() + filename0.substring(filename0.lastIndexOf("."));

        // 新建文件
        File filepath0 = new File(path, filename0);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath0.getParentFile().exists()) {
            filepath0.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            founderPhoto0.transferTo(new File(path + File.separator + filename0));
            //调用service，将公司基本信息存入数据库
            companyinitService.saveFounder(companyId, filename0, founderName0, founderPosition0, founderDescribe0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(founderName1!=null&&!"".equals(founderName1)){
            // 获取上传文件名
            String filename1 = founderPhoto1.getOriginalFilename();
            //生成新文件名
            filename1 = Tools.getUUID() + filename1.substring(filename1.lastIndexOf("."));

            // 新建文件
            File filepath1 = new File(path, filename1);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath1.getParentFile().exists()) {
                filepath1.getParentFile().mkdirs();
            }
            try {
                // 写入文件
                founderPhoto1.transferTo(new File(path + File.separator + filename1));
                //调用service，将公司基本信息存入数据库
                companyinitService.saveFounder(companyId, filename1, founderName1, founderPosition1, founderDescribe1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(founderName2!=null&&!"".equals(founderName2)){
            // 获取上传文件名
            String filename2 = founderPhoto2.getOriginalFilename();
            //生成新文件名
            filename2 = Tools.getUUID() + filename2.substring(filename2.lastIndexOf("."));

            // 新建文件
            File filepath2 = new File(path, filename2);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath2.getParentFile().exists()) {
                filepath2.getParentFile().mkdirs();
            }
            try {
                // 写入文件
                founderPhoto2.transferTo(new File(path + File.separator + filename2));
                //调用service，将公司基本信息存入数据库
                companyinitService.saveFounder(companyId, filename2, founderName2, founderPosition2, founderDescribe2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            response.sendRedirect("index02");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return "index02";
    }

}