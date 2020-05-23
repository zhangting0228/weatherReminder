package com.huawei.zhangting.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author 张挺
 * @Description
 * @Date 2019/12/13 8:53
 */
@Service
public class NotifyService {

    private static final String MSG_SEND_URL = "https://sc.ftqq.com/SCU68613Teece1b1fd4500d64bd38230103edca2b5df23e21cab09.send?";

    private static final String WEATHER_FETCH_URL = "https://wis.qq.com/weather/common?source=xw&weather_type=forecast_24h";

    private static final String PROVINCE = "江苏";

    private static final String CITY = "南京";

    @Resource
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 0 7 * * ?")
    public void excute() {
        JSONObject weather = restTemplate.getForEntity(WEATHER_FETCH_URL + "&province=" + PROVINCE + "&city=" + CITY, JSONObject.class).getBody();
        Map data = (HashMap) weather.get("data");
        Map map = (HashMap) data.get("forecast_24h");
        Map today = (HashMap) map.get("1");
        String forecast = "\r\r日期:" + today.get("time") + "    \r\r地区:南京    \r\r天气:" + today.get("day_weather")
                + "    \r\r温度:" + today.get("min_degree") + "℃ ~ " + today.get("max_degree") + "℃";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder msg = new StringBuilder();
        msg.append("text=天气预报提醒&desp=" + forecast + "    \r\r消息推送时间:" + format.format(new Date()));
        restTemplate.postForEntity(MSG_SEND_URL + msg.toString(), null, String.class).getBody();
    }

}
