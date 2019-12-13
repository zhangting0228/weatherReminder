package com.binfo.zhangting.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

/**
 * @Author 张挺（zhangting@binfo-tech.com）
 * @Description
 * @Date 2019/12/12 21:46
 */
public class Main {

    private static final String MSG_URL = "https://sc.ftqq.com/SCU68613Teece1b1fd4500d64bd38230103edca2b5df23e21cab09.send?text=天气预报提醒&desp=";
    private static final String WEATHER_URL = "https://wis.qq.com/weather/common?source=xw&weather_type=forecast_24h&province=江苏&city=南京";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        // 先获取天气
        JSONObject weather = restTemplate.getForEntity(WEATHER_URL, JSONObject.class).getBody();
        // 判断今日天气是否有雨
        LinkedHashMap data = (LinkedHashMap) weather.get("data");
        LinkedHashMap map = (LinkedHashMap) data.get("forecast_24h");
        LinkedHashMap today = (LinkedHashMap) map.get("1");
        String forecast = today.get("time") + " 南京 : " + today.get("day_weather") + "; " + today.get("min_degree") + "℃ ~ " + today.get("max_degree") + "℃";
        System.out.println(forecast);

        String str = restTemplate.postForEntity(MSG_URL + forecast, null, String.class).getBody();
        JSONObject jsonObject = JSONObject.parseObject(str);
        String errmsg = jsonObject.get("errmsg").toString();
        String dataset = jsonObject.get("dataset").toString();
        System.out.println("接口请求结果:" + errmsg);
        System.out.println("发送消息结果:" + dataset);

    }


}
