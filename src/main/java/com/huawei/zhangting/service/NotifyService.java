package com.huawei.zhangting.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;


/**
 * @Author 张挺
 * @Description
 * @Date 2019/12/13 8:53
 */
@Service
public class NotifyService {
    private static final String MSG_URL = "https://sc.ftqq.com/SCU68613Teece1b1fd4500d64bd38230103edca2b5df23e21cab09.send?";
    private static final String WEATHER_URL = "https://wis.qq.com/weather/common?source=xw&weather_type=forecast_24h&province=江苏&city=南京";
    @Resource
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 0 7 * * ?")
    public void excute() {
        JSONObject weather = restTemplate.getForEntity(WEATHER_URL, JSONObject.class).getBody();
        LinkedHashMap data = (LinkedHashMap) weather.get("data");
        LinkedHashMap map = (LinkedHashMap) data.get("forecast_24h");
        LinkedHashMap today = (LinkedHashMap) map.get("1");
        String forecast = "\r\r日期:" + today.get("time") + "    \r\r地区:南京    \r\r天气:" + today.get("day_weather") + "    \r\r温度:" + today.get("min_degree") + "℃ ~ " + today.get("max_degree") + "℃";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder msg = new StringBuilder();
        msg.append("text=天气预报提醒&desp=" + forecast + "    \r\r消息推送时间:" + format.format(new Date()));
        String str = restTemplate.postForEntity(MSG_URL + msg.toString(), null, String.class).getBody();
    }

}
