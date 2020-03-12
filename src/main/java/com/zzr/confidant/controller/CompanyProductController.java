package com.zzr.confidant.controller;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.service.CompanyProductService;
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
import java.io.File;
import java.io.IOException;

/**
 * @description 公司产品表(Companyproduct)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Api(tags = "公司产品上传操作")
@Controller
public class CompanyProductController {

    @Autowired
    private CompanyProductService companyproductService;

    //上传地址
    @Value("${file.upload.path}")
    private String filePath;//路径： /Users/zzr/images/

    /**
     * 新增产品信息
     * @param companyId 公司ID
     * @param userId 当前登陆人ID
     * @param productLogo 产品logo
     * @param productName 产品名称
     * @param productUrl 产品URL
     * @param productDescribe 产品介绍
     */
    @ApiOperation(value = "产品信息上传", notes = "开发：赵志然")
    @PostMapping("/saveProduct")
    public void saveProduct(@ApiParam(value = "公司ID") String companyId,
                            @ApiParam(value = "当前登陆人ID") String userId,
                            @ApiParam(value = "公司产品logo文件对象") MultipartFile productLogo,
                            @ApiParam(value = "产品名称") String productName,
                            @ApiParam(value = "产品地址") String productUrl,
                            @ApiParam(value = "产品简介") String productDescribe,
                            HttpServletRequest request, HttpServletResponse response){
//        System.out.println("公司ID:"+companyId);
//        System.out.println("当前登陆人ID:"+userId);
//        System.out.println("公司产品logo文件对象:"+productLogo.getOriginalFilename());
//        System.out.println("产品名称:"+productName);
//        System.out.println("产品地址:"+productUrl);
//        System.out.println("产品简介:"+productDescribe);
        // 获取上传文件名
        String filename = productLogo.getOriginalFilename();
        //生成新文件名
        filename= Tools.getUUID()+filename.substring(filename.lastIndexOf("."));
        //System.out.println("新文件名："+filename);

        // 定义上传文件保存路径 images/
        String path = filePath + "ProductLogo/";
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            productLogo.transferTo(new File(path + File.separator + filename));
            //调用service，将公司基本信息存入数据库
            ResultDTO result = companyproductService.saveProduct(companyId,userId, filename, productName, productUrl, productDescribe);
            if(result.getCode()==1){
                //失败，直接返回
                try {
                    response.sendRedirect("index02");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //成功则重定向到设置标签页面
        try {
            response.sendRedirect("index03");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @ApiOperation(value = "修改产品信息", notes = "开发：赵志然")
    @PostMapping("/resetProduct/{companyId}/{userId}/{newProductName}/{newProductUrl}/{newProductDescribe}")
    @ResponseBody
    public ResultDTO resetProduct(@ApiParam(value = "公司ID") @PathVariable("companyId") String companyId,
                                  @ApiParam(value = "当前登陆人ID") @PathVariable("userId") String userId,
                                  @ApiParam(value = "修改后的产品名") @PathVariable("newProductName")  String newProductName,
                                  @ApiParam(value = "修改后的产品网址") @PathVariable("newProductUrl")  String newProductUrl,
                                  @ApiParam(value = "修改后的产品介绍") @PathVariable("newProductDescribe")  String newProductDescribe){
        newProductUrl="'"+newProductUrl+"'";
        return companyproductService.resetProduct(companyId,userId,newProductName,newProductUrl,newProductDescribe);
    }


//    @ApiOperation(value = "修改产品信息", notes = "开发：赵志然")
//    @PostMapping("/resetProduct/{companyId}/{userId}/{newProductName}/{newProductUrl}")
//    @ResponseBody
//    public ResultDTO resetProduct(@ApiParam(value = "公司ID") @PathVariable("companyId") String companyId,
//                                  @ApiParam(value = "当前登陆人ID") @PathVariable("userId") String userId,
//                                  @ApiParam(value = "产品吗") @PathVariable("newProductName") String newProductName,
//                                  @ApiParam(value = "产品吗") @PathVariable("newProductUrl") String newProductUrl){
//        System.out.println("进入请求！！！！"+companyId+"         "+userId+"       "+newProductName+"   "+newProductUrl);
//        return null;
//    }
}