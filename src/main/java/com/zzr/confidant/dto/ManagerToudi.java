package com.zzr.confidant.dto;

import com.zzr.confidant.model.Deliver;
import com.zzr.confidant.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 企业用户查看投递的简历，返回的数据
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerToudi {

    /**
     * 简历信息
     */
    private Resume resume;

    /**
     * 简历投递ID
     */
    private Deliver deliver;
}
