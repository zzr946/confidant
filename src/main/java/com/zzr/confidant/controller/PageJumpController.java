package com.zzr.confidant.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: 跳转页面的请求 作为服务端重定项到指定页面
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/7
 */
@Api(tags = "只做跳转页面的操作")
@Controller
public class PageJumpController {

    /**
     * 普通用户点击logo,首页，进入到index页面
     */
    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }

    /**
     * 企业用户进行公司认证时，跳转到第二步，公司标签
     */
    @GetMapping("/tag")
    public String tag(){
        return "tag";
    }

    /**
     * 企业用户进行公司认证时，跳转到第三步，创始人页面founder
     */
    @GetMapping("/founder")
    public String founder(){
        return "founder";
    }

    /**
     * 企业用户进行公司认证时，跳转到第四步，公司产品
     */
    @GetMapping("/index02")
    public String index02(){
        return "index02";
    }

    /**
     * 企业用户进行公司认证时，跳转到第五步，公司介绍
     */
    @GetMapping("/index03")
    public String index03(){
        return "index03";
    }

    /**
     * 企业用户进行公司认证时，认证成功，跳转到成功页面
     */
    @GetMapping("/success")
    public String success(){
        return "success";
    }

    /**
     * 创建职位成功，跳转到成功页面
     */
    @GetMapping("/index06")
    public String index06(){
        return "index06";
    }

    /**
     * 跳转到有效职位页面
     */
    @GetMapping("/positions")
    public String positions(){
        return "positions";
    }

    /**
     * 跳转到修改密码页面
     */
    @GetMapping("/accountSetting")
    public String updatePwd(){
        return "updatePwd";
    }


    /********************************************************************************************/
    /****************************************普通用户相关*****************************************/

    /**
     * 跳转到简历预览页面
     */
//    @GetMapping("/preview")
//    public String preview(){
//        return "preview";
//    }




}
