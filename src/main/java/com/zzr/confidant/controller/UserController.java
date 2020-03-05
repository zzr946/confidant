package com.zzr.confidant.controller;

import com.zzr.confidant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 用户表(User)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


}