package com.zzr.confidant.controller;

import com.zzr.confidant.service.CompanyTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 公司标签表(Companytags)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@RestController
@RequestMapping("/api/companytags")
public class CompanytagsController {

    @Autowired
    private CompanyTagsService companytagsService;


}