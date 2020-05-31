package cn.jorian.jorianframework;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableCaching
@MapperScan("cn.jorian.jorianframework.core.*.mapper")
public class JorianFrameworkApplication {

    public static void main(String[] args) throws UnknownHostException {

        ConfigurableApplicationContext application = SpringApplication.run(JorianFrameworkApplication.class, args);
        Environment env = application.getEnvironment();

        String ip =InetAddress.getLocalHost().getHostAddress();
        String ssl = env.getProperty("server.ssl.key-store");
        String protocol ="http";
        if(ssl != null){
            protocol = "https";
        }
        String port = env.getProperty("server.port");
        // String ctpath = env.getProperty("server.servlet.context-path");
        System.out.println("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local: \t"+protocol+"://localhost:"+port+"/\n\t" +
                "External: "+protocol+"://"+ip+":"+port+"/\n\t" +
                "swagger-ui: "+protocol+"://"+ip+":"+port+"/doc.html\n" +
                "----------------------------------------------------------");
    }

}
