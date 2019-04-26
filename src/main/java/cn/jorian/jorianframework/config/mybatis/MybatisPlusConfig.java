package cn.jorian.jorianframework.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: jorian
 * @Date: 2019/4/25 16:13
 * @Description:
 */
//Spring boot方式
@EnableTransactionManagement
@Configuration
@MapperScan("cn.jorian.jorianframework.core.*.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

