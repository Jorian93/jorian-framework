package cn.jorian.jorianframework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: jorian
 * @Date: 2019/8/5 21:07
 * @Description:
 */
@Configuration
public class RestConfiguration {
    @Autowired
    RestTemplateBuilder builder;
    @Bean
    public RestTemplate restTemplate(){
        return builder.build();
    }
}
