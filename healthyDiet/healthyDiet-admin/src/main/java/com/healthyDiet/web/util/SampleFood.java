package com.healthyDiet.web.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import com.tencentcloudapi.tiia.v20190529.models.*;

import java.util.ArrayList;
import java.util.List;

public class SampleFood {
    public static List<String > DetectLabelPro (String args) {
        List<String > list = new ArrayList<>();
        try{
            Credential cred = new Credential("AKIDNcmrqdKFFLNLNHypjZIuKmAQjtyzsgZY", "3DwkV8YuGQWRDnANhEyaKvIRMCtOhrpm");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tiia.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            TiiaClient client = new TiiaClient(cred, "ap-beijing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DetectLabelProRequest req = new DetectLabelProRequest();
            req.setImageUrl(args);
            // 返回的resp是一个DetectLabelProResponse的实例，与请求对象对应
            DetectLabelProResponse resp = client.DetectLabelPro(req);
            // 输出json格式的字符串回包

            try {
                if(resp.getLabels().length > 0){
                    for (DetectLabelItem item:resp.getLabels()
                         ) {
                        if(item.getConfidence() > 65 && item.getFirstCategory().equals("食物")){

                            list.add(item.getName());
                        }
                    }
                }
                return list;
            }catch (Exception e){

            }
            System.out.println(DetectLabelProResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        return list;
    }
}