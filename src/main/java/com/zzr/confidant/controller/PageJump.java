package com.zzr.confidant.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: 跳转页面的请求
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/7
 */
@Api(tags = "只做跳转页面的操作")
@Controller
public class PageJump {

    /**
     * 企业用户点击 我的公司，进入到公司页面
     * @return
     */
    @GetMapping("/toMyHome")
    public String toMyHome(){
        return "myhome";
    }

    /**
     * 普通用户点击logo,首页，进入到index页面
     */
    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }


}
