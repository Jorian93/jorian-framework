package cn.jorian.jorianframework.EncryptPasswordTest;

import cn.jorian.jorianframework.common.utils.ToolEncryptPassword;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: jorian
 * @date: 2020/1/7 17:15
 * @description: this is  description for the class
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptPasswordTest {

    @Test
    public void encryptPassword(){
        String username = "jorian1";
        String password = "jorian1";

        System.out.println(ToolEncryptPassword.ENCRYPT_MD5(username,password));
    }

}
