package cn.jorian.jorianframework.http;

import cn.jorian.jorianframework.common.utils.HttpUtil;

/**
 * @Auther: jorian
 * @Date: 2019/8/1 14:05
 * @Description:
 */

public class httpTest {

    public static void main(String []args){
        HttpUtil httpUtil = new HttpUtil();
        String s = httpUtil.get();
        System.out.println(s);
    }


}
