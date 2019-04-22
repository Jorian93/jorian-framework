package cn.jorian.jorianframework.generateCodeTest;

import cn.jorian.jorianframework.config.mybatis.MybatisPlusGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: jorian
 * @Date: 2019/4/18 23:46
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenerateCodeTest {

    @Test
    public void generateCode(){
        MybatisPlusGeneratorConfig mybatisPlusGeneratorConfig =new MybatisPlusGeneratorConfig();
        mybatisPlusGeneratorConfig.generatorCode();
    }
}
