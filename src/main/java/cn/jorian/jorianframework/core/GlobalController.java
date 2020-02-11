package cn.jorian.jorianframework.core;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @Author: jorian
 * @Date: 2019/4/19 23:34
 * @Description:
 */
@Controller
@Api(tags = "全局控制器管理")
public class GlobalController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
