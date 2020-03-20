package com.zzr.confidant.dto;

import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.model.Deliver;
import com.zzr.confidant.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户查看已经投递的简历状态 返回到前端的数据
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToudiDTO {

    /**
     * 公司基本信息
     */
    private CompanyInfo companyInfo;

    /**
     * 简历信息
     */
    private Position position;

    /**
     * 简历投递记录信息
     */
    private Deliver deliver;
}
