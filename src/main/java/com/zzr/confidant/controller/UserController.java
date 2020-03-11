package com.zzr.confidant.controller;

import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.UserMapper;
import com.zzr.confidant.model.User;
import com.zzr.confidant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 赵志然
 * @description 用户表(User)表控制层
 * @date 2020-03-05 22:50:36
 */
@Api(tags = "用户登陆等相关业务")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @ApiOperation(value = "跳转到登陆页面", notes = "开发：赵志然")
    @GetMapping("/loginPage")
    public String toLoginPage() {
        return "login";
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @ApiOperation(value = "跳转到注册页面", notes = "开发：赵志然")
    @GetMapping("/registerPage")
    public String toregisterPage() {
        return "register";
    }

    /**
     * 登陆时忘记密码，跳转到修改密码页面
     *
     * @return
     */
    @ApiOperation(value = "跳转到修改密码页面", notes = "开发：赵志然")
    @GetMapping("/resetPage")
    public String toresetPage() {
        return "reset";
    }

    /**
     * 企业用户登陆后，如果已经认证则跳转到公司首页
     * @return
     */
    @ApiOperation(value = "跳转到公司首页 页面", notes = "开发：赵志然")
    @GetMapping("/myhome")
    public String tomyhomePage(){
        return "myhome";
    }

    /**
     * 企业用户登陆后，如果未认证则跳转到认证第一步
     * @return
     */
    @ApiOperation(value = "跳转到公司认证 页面", notes = "开发：赵志然")
    @GetMapping("/index01")
    public String toindex01Page(@ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userId") String userId){
        //判断用户上次是否有未操作完的步骤
        //调用service层校验
        System.out.println("*******:"+userId);
        userService.checkAuthentication(userId);
        //跳转到认证第一步
        return "index01";
    }




    /**
     * 获取短信验证码
     *
     * @param phone 电话号码
     * @return
     */
    @ApiOperation(value = "获取短信验证码", notes = "开发：赵志然")
    @PostMapping("/getCode/{phone}")
    @ResponseBody
    public ResultDTO getCode(@ApiParam(value = "手机号码") @PathVariable(value = "phone") String phone) {
        //System.out.println("电话号码："+phone);
        //调用业务层，发送短信
        return userService.sendPhoneCode(phone);
    }

    /**
     * 用户注册的方法
     *
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "开发：赵志然")
    @PostMapping(value = "/reg/{type}/{phone}/{code}/{pwd}")
    @ResponseBody
    public ResultDTO register(@ApiParam(value = "用户类型") @PathVariable(value = "type") String type,
                              @ApiParam(value = "电话号码，登陆账号") @PathVariable(value = "phone") String phone,
                              @ApiParam(value = "用户输入的验证码") @PathVariable(value = "code") String code,
                              @ApiParam(value = "密码") @PathVariable(value = "pwd") String pwd) {
        return userService.register(type,phone,code,pwd);
    }

    /**
     * 用户登陆的方法
     * @param phone 电话号码，登陆账号
     * @param pwd 登陆密码
     * @return
     */
    @ApiOperation(value = "用户登陆", notes = "开发：赵志然")
    @PostMapping("/login/{phone}/{pwd}")
    @ResponseBody
    public ResultDTO login(@ApiParam(value = "电话号码，登陆账号") @PathVariable(value = "phone") String phone,
                           @ApiParam(value = "登陆密码") @PathVariable(value = "pwd") String pwd,
                           HttpServletRequest request){
        ResultDTO result = userService.login(phone, pwd);
        //获取session
        HttpSession session = request.getSession();
        if(result.getData()!=null){
            //登陆成功，将user信息存入session
            session.setAttribute("user",result.getData());
        }
        //登陆失败,直接将result返回
        return result;
    }

    /**
     * 用户通过短信验证码修改密码
     * @param phone 手机号
     * @param code 用户输入的验证码
     * @param pwd 新密码
     * @return
     */
    @ApiOperation(value = "用户通过短信验证码修改密码", notes = "开发：赵志然")
    @PostMapping("/resetpassword/{phone}/{code}/{pwd}")
    @ResponseBody
    public ResultDTO resetPassword(@ApiParam(value = "电话号码，登陆账号") @PathVariable(value = "phone") String phone,
                                   @ApiParam(value = "验证码") @PathVariable(value = "code") String code,
                                   @ApiParam(value = "密码") @PathVariable(value = "pwd") String pwd){
        return userService.resetPassword(phone,code,pwd);
    }

    /**
     * 退出登陆的方法
     * @return
     */
    @ApiOperation(value = "退出登陆", notes = "开发：赵志然")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        //获取session
        HttpSession session=request.getSession();
        //清空session
        session.removeAttribute("user");
        //重定向，返回到主页面
        try {
            response.sendRedirect("index");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}