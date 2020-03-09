package com.zzr.confidant.controller;

import com.zzr.confidant.service.CompanyInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 公司创始人表(Companyinit)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@RestController
@RequestMapping("/api/companyinit")
public class CompanyInitController {

    @Autowired
    private CompanyInitService companyinitService;


}