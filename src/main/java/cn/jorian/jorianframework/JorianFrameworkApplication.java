package cn.jorian.jorianframework;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableCaching
@MapperScan("cn.jorian.jorianframework.core.*.mapper")
@EnableElasticsearchRepositories(basePackages = "cn.jorian.jorianframework.core.elsearch.mapper*")
public class JorianFrameworkApplication {

    public static void main(String[] args) {
        //这句是为了解决es报 netty错误
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(JorianFrameworkApplication.class, args);
        log.info("》》》应用启动......");
    }

}
