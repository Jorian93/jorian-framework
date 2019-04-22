package cn.jorian.jorianframework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableCaching
@MapperScan("cn.jorian.jorianframework.core.*.mapper")
public class JorianFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(JorianFrameworkApplication.class, args);
    }

}
