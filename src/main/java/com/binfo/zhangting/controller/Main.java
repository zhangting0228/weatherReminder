package com.binfo.zhangting.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 张挺（zhangting@binfo-tech.com）
 * @Description
 * @Date 2019/12/12 21:46
 */
public class Main {

    private static final String URL = "https://sc.ftqq.com/SCU68613Teece1b1fd4500d64bd38230103edca2b5df23e21cab09.send?text=测试中1232322222&desp=哈哈哈哈哈测试中测试中测试中哈哈哈哈哈哈哈哈哈哈";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.postForEntity(URL, null, String.class).getBody();
        JSONObject jsonObject = JSONObject.parseObject(str);
        String errmsg = jsonObject.get("errmsg").toString();
        String dataset = jsonObject.get("dataset").toString();
        System.out.println(errmsg);
        System.out.println(dataset);
    }


}
