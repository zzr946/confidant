package com.zzr.confidant.controller;

import com.zzr.confidant.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 公司表基本信息表(Companyinfo)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@RestController
@RequestMapping("/api/companyinfo")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyinfoService;


}