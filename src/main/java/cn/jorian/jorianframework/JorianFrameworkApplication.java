package cn.jorian.jorianframework;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author jorian
 */
@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableCaching
@MapperScan("cn.jorian.jorianframework.core.*.mapper")
@EnableElasticsearchRepositories(basePackages = "cn.jorian.jorianframework.core.elsearch.mapper*")
public class JorianFrameworkApplication {

    public static void main(String[] args) throws UnknownHostException {
        //这句是为了解决es报 netty错误
        System.setProperty("es.set.netty.runtime.available.processors", "false");

        ConfigurableApplicationContext application = SpringApplication.run(JorianFrameworkApplication.class, args);
        Environment env = application.getEnvironment();

        String ip =InetAddress.getLocalHost().getHostAddress();
        String ssl = env.getProperty("server.ssl.key-store");
        String protocol ="http";
        if(ssl != null){
            protocol = "https";
        }
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}/\n\t" +
                "External: \t{}://{}:{}/\n\t" +
                "swagger-ui: {}://{}:{}/doc.html\n" +
                "----------------------------------------------------------",
                protocol,port,protocol,ip,port,protocol,ip,port);
    }

}
