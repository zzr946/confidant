package com.zzr.confidant.controller;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.service.CompanyInfoService;
import com.zzr.confidant.tool.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;

/**
 * @description 公司表基本信息表(Companyinfo)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Api(tags = "处理文件上传操作")
@Controller
public class CompanyInfoController {

    @Autowired
    CompanyInfoService companyInfoService;

    //上传地址
    @Value("${file.upload.path}")
    private String filePath;//路径： /Users/zzr/images/

    @ApiOperation(value = "公司logo上传", notes = "开发：赵志然")
    @PostMapping("/savePic")
    //@ResponseBody
    public void upload(@ApiParam(value = "公司名称") String name,
                         @ApiParam(value = "公司logo文件对象") MultipartFile companyPhoto,
                         @ApiParam(value = "公司网址") String companyUrl,
                         @ApiParam(value = "公司所在城市") String companyCity,
                         @ApiParam(value = "公司所属行业领域") String companyField,
                         @ApiParam(value = "公司规模") String companyScale,
                         @ApiParam(value = "公司发展阶段") String companyStage,
                         HttpServletRequest request,HttpServletResponse response) {
        System.out.println("文件上传请求进来了！！");
//        System.out.println("公司名称："+name);
//        System.out.println("公司logo文件对象："+companyPhoto);
//        System.out.println("公司网址："+companyUrl);
//        System.out.println("公司所在城市："+companyCity);
//        System.out.println("公司所属行业领域："+companyField);
//        System.out.println("公司规模："+companyScale);
//        System.out.println("公司发展阶段："+companyStage);
        //获取session
        HttpSession session = request.getSession();
        if (companyPhoto.isEmpty()) {
            //未设置公司logo则设置默认logo
            session.setAttribute("notSetLogo","未设置logo");
            //重定向到原来页面
            try {
                response.sendRedirect("index01");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //return "index01";
        }

        // 获取上传文件名
        String filename = companyPhoto.getOriginalFilename();
        //生成新文件名
        filename= Tools.getUUID()+filename.substring(filename.lastIndexOf("."));
        //System.out.println("新文件名："+filename);

        // 定义上传文件保存路径 images/
        String path = filePath + "comanyPhoto/";
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            companyPhoto.transferTo(new File(path + File.separator + filename));
            //调用service，将公司基本信息存入数据库
            ResultDTO result = companyInfoService.saveCompanyInfo(name, filename, companyUrl, companyCity, companyField, companyScale, companyStage);
            if(result.getCode()==1){
                //失败，直接返回
                try {
                    response.sendRedirect("index01");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(result.getCode()==0){
                //成功，将公司基本信息存入session
                session.setAttribute("reg_companyInfo",result.getData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //成功则重定向到设置标签页面
        try {
            response.sendRedirect("tag");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}