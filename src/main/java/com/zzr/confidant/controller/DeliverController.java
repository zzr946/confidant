package com.zzr.confidant.controller;

import com.zzr.confidant.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 简历投递表(Deliver)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@RestController
@RequestMapping("/api/deliver")
public class DeliverController {

    @Autowired
    private DeliverService deliverService;


}