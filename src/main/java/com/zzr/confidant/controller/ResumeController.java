package com.zzr.confidant.controller;

import com.zzr.confidant.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 简历表(Resume)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;


}