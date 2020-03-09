package com.zzr.confidant.controller;

import com.zzr.confidant.service.CompanyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 公司产品表(Companyproduct)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@RestController
@RequestMapping("/api/companyproduct")
public class CompanyProductController {

    @Autowired
    private CompanyProductService companyproductService;


}