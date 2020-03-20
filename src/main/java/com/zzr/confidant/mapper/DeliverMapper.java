package com.zzr.confidant.mapper;

import com.zzr.confidant.model.Deliver;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * @description 简历投递表(Deliver)表Mapper接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
public interface DeliverMapper extends BaseMapper<Deliver> {

    @Update("update deliver set deliverState='3' where reserved1=#{positionId}")
    int delState(String positionId);

    @Update("update deliver set deliverState='1' where id=#{toudiId}")
    void inform(String toudiId);

    @Update("update deliver set deliverState='2' where id=#{toudiId}")
    void notSuitable(String toudiId);

    @Update("update deliver set deliverState='3' where id=#{toudiId}")
    void del(String toudiId);
}