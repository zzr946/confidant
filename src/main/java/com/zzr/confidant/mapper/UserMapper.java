package com.zzr.confidant.mapper;

import com.zzr.confidant.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @description 用户表(User)表Mapper接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据短信修改密码
     * @param phone 手机号
     * @param pwd 新密码
     * @return
     */
    int resetPassword(String phone,String pwd);

    /**
     * 将企业用户认证状态改为已认证
     * @param userId
     */
    void updateAuthentiction(String userId);

    @Update("update user set password=#{newPwd} where id=#{userId} ")
    int updatePwd(String userId, String newPwd);
}