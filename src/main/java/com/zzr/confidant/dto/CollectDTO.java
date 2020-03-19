package com.zzr.confidant.dto;

import com.zzr.confidant.model.CompanyInfo;
import com.zzr.confidant.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户搜藏职位返回数据
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectDTO {

    /**
     * 收藏ID
     */
    private String collectId;

    /**
     * 职位所属公司基本信息
     */
    private CompanyInfo companyInfo;

    /**
     * 职位item
     */
    private Position position;
}
