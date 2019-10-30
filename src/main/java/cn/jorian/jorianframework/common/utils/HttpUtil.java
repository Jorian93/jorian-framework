package cn.jorian.jorianframework.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: jorian
 * @Date: 2019/8/5 21:09
 * @Description:
 */
public class HttpUtil {

    public String get(){
        RestTemplate restTemplate = new RestTemplate();
        String uri="http://www.weather.com.cn/data/sk/101010100.html";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
        return strbody;
    }

}
