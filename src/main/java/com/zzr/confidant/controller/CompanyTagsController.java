package com.zzr.confidant.controller;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.service.CompanyTagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 公司标签表(Companytags)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@Api(tags = "与公司标签相关的操作")
@RestController
public class CompanyTagsController {

    @Autowired
    private CompanyTagsService companytagsService;

    /**
     * 添加公司标签的方法
     * @param labels
     * @return
     */
    @ApiOperation(value = "新增公司标签", notes = "开发：赵志然")
    @PostMapping("/saveTags")
    public ResultDTO saveTags(@ApiParam(value = "所有标签") @RequestParam("labels") String labels,
                              @ApiParam(value = "公司id") @RequestParam("companyId")  String companyId){
        //System.out.println("labels："+labels);
        //System.out.println("companyId："+companyId);
        //调用server层，将标签存入数据库
        return companytagsService.saveTags(labels,companyId);
    }


}