package com.binfo.zhangting.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import org.slf4j.Logger;

/**
 * @Author 张挺（zhangting@binfo-tech.com）
 * @Description
 * @Date 2019/12/13 8:53
 */
@Service
public class NotifyService {

    private static final Logger logger = LoggerFactory.getLogger(NotifyService.class);

    private static final String MSG_URL = "https://sc.ftqq.com/SCU68613Teece1b1fd4500d64bd38230103edca2b5df23e21cab09.send?";
    private static final String MSG_URL_CL = "https://sc.ftqq.com/SCU68639T3fcfc4ff893c93247e99eeeede330a035df2eb1486677.send?";
    private static final String WEATHER_URL = "https://wis.qq.com/weather/common?source=xw&weather_type=forecast_24h&province=江苏&city=南京";

    @Resource
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 0 7 * * ?")
    public void excute() {
        //0 0 7 * * ?  - 每天七点
        //0 0/1 * * * ? - 每分钟一次
        logger.info("开始获取天气数据======================");
        // 获取天气
        JSONObject weather = restTemplate.getForEntity(WEATHER_URL, JSONObject.class).getBody();
        logger.info("完成获取天气数据======================" + weather);
        LinkedHashMap data = (LinkedHashMap) weather.get("data");
        LinkedHashMap map = (LinkedHashMap) data.get("forecast_24h");
        LinkedHashMap today = (LinkedHashMap) map.get("1");
        String forecast = "\r\r日期:" + today.get("time") + "    \r\r地区:南京    \r\r天气:" + today.get("day_weather") + "    \r\r温度:" + today.get("min_degree") + "℃ ~ " + today.get("max_degree") + "℃";
        logger.info(forecast);

        // 发送微信消息
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("开始调用消息发送接口==================");
        StringBuilder msg = new StringBuilder();
        msg.append("text=天气预报提醒&desp=" + forecast + "    \r\r消息推送时间:" + format.format(new Date()));
        StringBuilder msgForCl = new StringBuilder();
        msgForCl.append("text=天气预报提醒&desp=操磊你好，你的爸爸提醒你！！\r\r" + forecast + "    \r\r消息推送时间:" + format.format(new Date()));
        // 给我推
        String str = restTemplate.postForEntity(MSG_URL + msg.toString(), null, String.class).getBody();
        // 给操磊推
        String strCl = restTemplate.postForEntity(MSG_URL_CL + msgForCl.toString(), null, String.class).getBody();
        JSONObject jsonObject = JSONObject.parseObject(str);
        logger.info("发送消息结束==================");
        String errmsg = jsonObject.get("errmsg").toString();
        String dataset = jsonObject.get("dataset").toString();
        logger.info("接口请求结果:" + errmsg);
        logger.info("发送消息结果:" + dataset);
    }

}
