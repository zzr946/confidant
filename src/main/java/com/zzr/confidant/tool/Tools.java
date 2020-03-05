package com.zzr.confidant.tool;


import org.apache.commons.codec.digest.DigestUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


/**
 * 通用工具类
 */
public class Tools {

    /**
     * 日期格式转为字符串
     *
     * @param path 格式
     * @param date 日期
     */
    public static String dateToStr(String path, Date date) {
        return new SimpleDateFormat(path).format(date);
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 密码加密
     *
     * @param password 需要加密的密码
     * @return 加密后的密码
     */
    public static String getMD5(String password) {
        return DigestUtils.md5Hex(password);
    }


    /**
     * 获取随机的10为数
     *
     * @return
     */
    public static String getNum() {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 10; i++) {
            int n = new Random().nextInt(10);
            sb.append(n + "");
        }
        return sb.toString();
    }

}
