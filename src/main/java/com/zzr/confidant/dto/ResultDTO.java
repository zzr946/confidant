package com.zzr.confidant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:返回到前端的数据统一格式
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private Integer code;
    private String msg;
    private Object data;
}
