package cn.jorian.jorianframework.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: jorian
 * @Date: 2019/7/9 16:08
 * @Description:
 */
@Slf4j
public class JTool_HttpClient {

    /**
     *
     * @param url
     * @param parmas 请求参数
     * @param method  1.GET,get请求 2.POST, post请求
     * @return
     */
    public String send(String url, Map<String,String> parmas,String method){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String result = "请求未执行！";

        if("GET".equals(method)){
            result = this.doGet(url,parmas,entity);
        }
        if("POST".equals(method)){
            result = this.doPost(url,parmas,entity);
        }
        return result;
    }

    /**
     * get请求
     * @param url
     * @param params
     * @param entity
     * @return
     */
    String doGet(String url, Map params,  HttpEntity<String> entity){
        RestTemplate restTemplate = new RestTemplate();
        StrBuilder sb = new StrBuilder();
        params.forEach((k,v)->{
           sb.append(k);
           sb.append("=");
           sb.append(v);
           sb.append("&");
        });
        url = url+"?"+sb.toString();
        log.info("发送get请求："+url);
        String strbody = restTemplate.exchange(url, HttpMethod.GET, entity,String.class).getBody();
        return strbody;
    }

    /**
     * post请求
     * @param url
     * @param params
     * @param entity
     * @return
     */
    String doPost(String url, Map params,  HttpEntity<String> entity){
        RestTemplate restTemplate = new RestTemplate();
        log.info("发送post请求："+url);
        String strbody = restTemplate.exchange(url, HttpMethod.POST, entity,String.class).getBody();
        return strbody;
    }



}
