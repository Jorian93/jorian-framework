package cn.jorian.jorianframework.redis;

import cn.jorian.jorianframework.config.mybatis.MybatisPlusGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: jorian
 * @Date: 2019/5/14 14:44
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class redisTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void strRedis(){
        stringRedisTemplate.opsForValue().set("name","张三aaa");
        String name = stringRedisTemplate.opsForValue().get("name");
        String tok = stringRedisTemplate.opsForValue().get("J-Token");
        log.info(name);
        log.info(tok);
}


}
