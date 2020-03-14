package com.zzr.confidant.mapper;

import com.zzr.confidant.model.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * @description 职位表(Position)表Mapper接口
 * @author 赵志然
 * @date 2020-03-05 22:50:36
 */
public interface PositionMapper extends BaseMapper<Position> {

    @Update("update position set positionState='1' where id=#{positionId}")
    int offline(String positionId);

    @Update("update position set positionState='2' where id=#{positionId}")
    int delPosition(String positionId);

    @Update("update position set positionState='0',publishTime=#{time} where id=#{positionId}")
    int reOnline(String positionId,String time);
}