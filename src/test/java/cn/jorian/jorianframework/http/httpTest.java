package cn.jorian.jorianframework.http;

import cn.jorian.jorianframework.common.utils.JTool_HttpClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: jorian
 * @Date: 2019/8/1 14:05
 * @Description:
 */
@Slf4j
@SpringBootTest
public class httpTest {

    @Test
    public void httpGetTest(){
        JTool_HttpClient httpClient = new JTool_HttpClient();
        Map parmas = new HashMap<String,String>();
        parmas.put("name","好久不见");
        String s = httpClient.send("https://api.apiopen.top/searchMusic", parmas,"GET");
        log.info(s);
    }

    @Test
    public void httpGetTest2(){
        JTool_HttpClient httpClient = new JTool_HttpClient();
        Map parmas = new HashMap<String,String>();

        Map m = new HashMap<String,String >();
        m.put("SystemId","1");
        m.put("UserPass","111111");
        m.put("UserAccount","nbswbs");
        String jstrm = JSON.toJSONString(m);
        parmas.put("m",jstrm);
        String s = httpClient.send("http://112.14.33.182:8888//com.wisesoft.canal.api/System/LoginValidate.json", parmas,"GET");
        log.info(s);
    }


}
