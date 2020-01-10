package cn.jorian.jorianframework.EncryptPasswordTest;

import cn.jorian.jorianframework.common.utils.JTool_EncryptPassword;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: jorian
 * @date: 2020/1/7 17:15
 * @description: this is  description for the class
 */
@Slf4j
@SpringBootTest
public class EncryptPasswordTest {

    @Test
    public void encryptPassword(){
        String username = "jorian1";
        String password = "jorian1";

        System.out.println(JTool_EncryptPassword.ENCRYPT_MD5(username,password));
    }

}
