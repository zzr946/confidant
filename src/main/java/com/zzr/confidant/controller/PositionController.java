package com.zzr.confidant.controller;

import com.zzr.confidant.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 职位表(Position)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@RestController
@RequestMapping("/api/position")
public class PositionController {

    @Autowired
    private PositionService positionService;


}