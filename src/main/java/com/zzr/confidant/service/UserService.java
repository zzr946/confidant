package com.zzr.confidant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.UserMapper;
import com.zzr.confidant.model.User;
import com.zzr.confidant.tool.PhoneCode;
import com.zzr.confidant.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵志然
 * @description 用户表(User)表服务接口
 * @date 2020-03-05 22:50:36
 */
@Service
public class UserService {
    //临时存放短信验证码
    private String phoneCode;

    @Resource
    UserMapper userMapper;

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @return
     */
    public synchronized ResultDTO sendPhoneCode(String phone) {
        ResultDTO result=new ResultDTO();
        //获取六位数的验证码，并保存下来
        phoneCode=PhoneCode.getCode();
        //发送短信
        PhoneCode.sendCode(phone,phoneCode);
        result.setCode(0);
        result.setMsg("发送成功");
        result.setData(phoneCode);
        return result;
    }

    /**
     * 用户注册的方法
     * @param type 用户类型
     * @param phone 电话号码
     * @param code 验证码
     * @param pwd 密码
     * @return
     */
    public ResultDTO register(String type, String phone, String code, String pwd) {
        ResultDTO result=new ResultDTO();
        List<User> list = new ArrayList<>();
        User user = new User();
        //注册之前先判断该手机号是否已经注册过了
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        list = userMapper.selectList(queryWrapper);
//        System.out.println("验证手机号是否已经注册过："+list.isEmpty());//true
//        System.out.println("验证手机号是否已经注册过?"+list==null);//false
        if(!list.isEmpty()){
            //查询结果不为空，则说明该手机号已经注册过了
            result.setCode(-1);
            result.setMsg("该手机号已经注册过了");
            result.setData(null);
            return result;
        }

        //二次校验，判断验证码是否正确
//        System.out.println("生成的验证码："+phoneCode);
//        System.out.println("用户输入的验证码："+code);
        if(!StringUtils.equals(code,phoneCode)){
            result.setCode(0);
            result.setMsg("后端校验，验证码不正确！");
            result.setData(null);
        }else{
            //验证码正确，则将用户信息存入数据库
            user.setId(Tools.getUUID());
            user.setPhone(phone);
            user.setPassword(Tools.getMD5(pwd));
            user.setUserType(type);
            //新注册的用户不管是普通用户还是企业用户，均为未认证状态
            user.setAuthentication("0");
            //新注册的用户均未登陆状态
            user.setLoginState("1");
            //调用mapper，将user存入数据库
            int i = userMapper.insert(user);
            if(i==1){
                //数据插入成功
                result.setCode(1);
                result.setMsg("注册成功!");
                result.setData(user);
            }
        }
        return result;
    }
}