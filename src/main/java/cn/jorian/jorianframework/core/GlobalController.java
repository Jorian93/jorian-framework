package cn.jorian.jorianframework.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @Author: jorian
 * @Date: 2019/4/19 23:34
 * @Description:
 */
@Controller
public class GlobalController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
