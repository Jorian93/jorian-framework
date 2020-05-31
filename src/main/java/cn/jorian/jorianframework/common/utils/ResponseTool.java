package cn.jorian.jorianframework.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;

/*
 * @Description: 
 * @Author: jorian
 * @Date: 2020-05-30 21:28:36
 */
public class ResponseTool {

    public static void write(HttpServletResponse response, Integer code, String msg) {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(ResponseResult.builder().code(code).msg(msg).build()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void write(HttpServletResponse response, ResponseCode responseCode) {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().write(
                    JSON.toJSONString(ResponseResult.builder().code(responseCode.code).msg(responseCode.msg).build()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
