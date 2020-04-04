package com.zzr.confidant.tool;


import java.util.Random;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @description:
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/6
 */
public class PhoneCode {
    /**
     * @param phone 手机号码
     * @param code  发送的验证码
     */
    public static void sendCode(String phone, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<AccessKey ID>",
                "<AccessKey Secret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "<SignName>");
        request.putQueryParameter("TemplateCode", "<TemplateCode>");

        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 获取6位数的随机验证码
     */
    public static String getCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; sb.length() < 6; i++) {
            int num = new Random().nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }
}
