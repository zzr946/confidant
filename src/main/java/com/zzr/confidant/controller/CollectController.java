package com.zzr.confidant.controller;

import com.zzr.confidant.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 职位收藏表(Collect)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:35
 */
@RestController
@RequestMapping("/api/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;


}