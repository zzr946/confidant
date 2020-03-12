package com.zzr.confidant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.mapper.*;
import com.zzr.confidant.model.*;
import com.zzr.confidant.tool.PhoneCode;
import com.zzr.confidant.tool.Tools;
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

    @Resource
    CompanyInfoMapper companyInfoMapper;
    @Resource
    CompanyTagsMapper companyTagsMapper;
    @Resource
    CompanyInitMapper companyinitMapper;
    @Resource
    CompanyProductMapper companyProductMapper;


    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return
     */
    public synchronized ResultDTO sendPhoneCode(String phone) {
        ResultDTO result = new ResultDTO();
        //获取六位数的验证码，并保存下来
        phoneCode = PhoneCode.getCode();
        //发送短信
        //PhoneCode.sendCode(phone,phoneCode);
        result.setCode(0);
        result.setMsg("发送成功");
        result.setData(phoneCode);
        return result;
    }

    /**
     * 用户注册的方法
     *
     * @param type  用户类型
     * @param phone 电话号码
     * @param code  验证码
     * @param pwd   密码
     * @return
     */
    public ResultDTO register(String type, String phone, String code, String pwd) {
        ResultDTO result = new ResultDTO();
        List<User> list = new ArrayList<>();
        User user = new User();
        //注册之前先判断该手机号是否已经注册过了
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        list = userMapper.selectList(queryWrapper);
//        System.out.println("验证手机号是否已经注册过："+list.isEmpty());//true
//        System.out.println("验证手机号是否已经注册过?"+list==null);//false
        if (!list.isEmpty()) {
            //查询结果不为空，则说明该手机号已经注册过了
            result.setCode(-1);
            result.setMsg("该手机号已经注册过了");
            result.setData(null);
            return result;
        }

        //二次校验，判断验证码是否正确
//        System.out.println("生成的验证码："+phoneCode);
//        System.out.println("用户输入的验证码："+code);
        if (!StringUtils.equals(code, phoneCode)) {
            result.setCode(0);
            result.setMsg("后端校验，验证码不正确！");
            result.setData(null);
        } else {
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
            if (i == 1) {
                //数据插入成功
                result.setCode(1);
                result.setMsg("注册成功!");
                result.setData(user);
            }
        }
        return result;
    }

    /**
     * 用户登陆的方法
     *
     * @param phone 手机号，也是登陆账号
     * @param pwd   登陆密码
     * @return
     */
    //@Cacheable(value = "user",key = "'userlogin['+#phone+']'")
    public ResultDTO login(String phone, String pwd) {
        ResultDTO result = new ResultDTO();
        List<User> list = new ArrayList<>();
        User user = null;
        //调用mpper 查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone).eq("password", Tools.getMD5(pwd));
        user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            //登陆失败
            result.setCode(-1);
            result.setMsg("用户名或密码错误!");
            result.setData(null);
        } else {
            //登陆成功
            result.setCode(0);
            result.setMsg("登陆成功!");
            result.setData(user);
            //myRedisTemplate.opsForValue().set("login_user",user);
        }
        return result;
    }

    /**
     * 用户通过短信验证码修改密码
     *
     * @param phone 手机号
     * @param code  验证码
     * @param pwd   新密码
     * @return
     */
    //@CachePut(value = "user",key = "'userlogin['+#phone+']'")
    public ResultDTO resetPassword(String phone, String code, String pwd) {
        ResultDTO result = new ResultDTO();
        User u = null;
        //二次校验，判断验证码是否正确
        if (!StringUtils.equals(code, phoneCode)) {
            result.setCode(0);
            result.setMsg("后端校验，验证码不正确！");
            result.setData(null);
            return result;
        }
        //先查询该手机号是否已经注册
        u = userMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
        if (u == null) {
            //该手机号未注册
            result.setCode(-1);
            result.setMsg("该手机号未注册，请前往注册!");
            result.setData(null);
            return result;
        }
        //手机号已经注册过，则执行修改密码的操作
        pwd = Tools.getMD5(pwd);
        int i = userMapper.resetPassword(phone, pwd);
        if (i == 1) {
            result.setCode(1);
            result.setMsg("密码已重置!");
            result.setData(null);
            //根据phone查询user，存入缓存
            //myRedisTemplate.opsForValue().set("login_user", userMapper.selectOne(new QueryWrapper<User>().eq("phone",phone)));


        } else {
            result.setCode(2);
            result.setMsg("系统异常!");
            result.setData(null);
        }

        return result;
    }

    /**
     * 未认证企业用户登陆后，判断之前是否填写过认证信息
     * @param userId 当前登陆的用户ID
     * @return
     */
    public void checkAuthentication(String userId) {
        List<CompanyInfo> list=null;
        //根据用户ID查询公司基本信息表
        list = companyInfoMapper.selectList(new QueryWrapper<CompanyInfo>().eq("reserved1", userId));
        System.out.println("list:"+list);
        if(list!=null){
            //如果查询出来结果，就将公司信息表中相关的数据全部删除
            //获取公司id
            list.forEach(s->{
                //根据ID删除
                companyInfoMapper.deleteById(s.getId());
                companyTagsMapper.delete(new QueryWrapper<CompanyTags>().eq("companyId",s.getId()));
                companyinitMapper.delete(new QueryWrapper<CompanyInit>().eq("companyId",s.getId()));
                companyProductMapper.delete(new QueryWrapper<CompanyProduct>().eq("companyId",s.getId()));
            });
        }
    }
}