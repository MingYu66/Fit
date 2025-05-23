package com.healthyDiet.web.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.healthyDiet.web.util.BaikeParser.parseBaike;

public class Sample
{
    public static void main(String [] args) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential("AKIDNcmrqdKFFLNLNHypjZIuKmAQjtyzsgZY", "3DwkV8YuGQWRDnANhEyaKvIRMCtOhrpm");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();

            req.setImageUrl("https://devicetest.enjoys.live/Snipaste_2024-03-03_19-32-14.png");

            // 返回的resp是一个GeneralBasicOCRResponse的实例，与请求对象对应
            GeneralBasicOCRResponse resp = client.GeneralBasicOCR(req);
            try{
                String str = "";
                if(resp.getTextDetections().length>0){
                    for (TextDetection item: resp.getTextDetections()
                    ) {
                        str = str + item.getDetectedText();
                    }
                }
                // 输出json格式的字符串回包
                System.out.println(str);

                for (String item: formString(str)
                     ) {
                    System.out.println(item  + "：" + parseBaike(item));
                }

            }catch (Exception e){
                System.out.println("解析失败");
            }


        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }


    public static List<BurdenSheet> AIBurdenSheet(String img) {
        List<BurdenSheet> sheets = new ArrayList<>();

        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential("AKIDNcmrqdKFFLNLNHypjZIuKmAQjtyzsgZY", "3DwkV8YuGQWRDnANhEyaKvIRMCtOhrpm");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();

//            req.setImageUrl("https://devicetest.enjoys.live/Snipaste_2024-03-03_19-32-14.png");
            req.setImageUrl(img);
            // 返回的resp是一个GeneralBasicOCRResponse的实例，与请求对象对应
            GeneralBasicOCRResponse resp = client.GeneralBasicOCR(req);
            try{
                String str = "";
                if(resp.getTextDetections().length>0){
                    for (TextDetection item: resp.getTextDetections()
                    ) {
                        str = str + item.getDetectedText();
                    }
                }
                // 输出json格式的字符串回包
                System.out.println(str);
                for (String item: formString(str)
                ) {
                    BurdenSheet sheet = new BurdenSheet();
                    sheet.setName(item);
                    sheet.setDesc(parseBaike(item));
                    System.out.println(sheet.getName()  + "：" + sheet.getDesc());
                    sheets.add(sheet);
                }
                return sheets;
            }catch (Exception e){
                System.out.println("解析失败");
                BurdenSheet sheet = new BurdenSheet();
                sheet.setName("解析失败");
                sheet.setDesc(e.toString());
                sheets.add(sheet);

                return sheets;
            }


        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            BurdenSheet sheet = new BurdenSheet();
            sheet.setName("解析失败");
            sheet.setDesc(e.toString());
            sheets.add(sheet);

            return sheets;
        }

    }
    public static List<String> formString(String input){

        // 找到第一个冒号的位置
        int colonIndex = input.indexOf(":");
        // 如果找到了冒号，截取后面的字符串
        String ingredients = colonIndex != -1 ? input.substring(colonIndex + 1) : input;

        // 将字符串按逗号分割成数组
        String[] ingredientsArray = ingredients.split("、");

        // 将数组转换为列表
        List<String> ingredientList = Arrays.asList(ingredientsArray);

        return ingredientList;
    }

}