package cn.jorian.jorianframework.core.account.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.config.jwt.JToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: jorian
 * @Date: 2019/4/17 17:33
 * @Description:
 */
public class ExecuteLoginService {

    /**
     * 普通的登入校验，校验token
     * @param request
     * @return
     */
    public static boolean executeLogin(ServletRequest request){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取token
        String token = httpServletRequest.getHeader("J-Token");
        if(token==null || "".equals(token.trim())){
            throw new ServiceException(ResponseCode.PERMISSIN_FAIL.msg,new AuthenticationException(),false,true);
        }
        //构造token传入shiro进行认证
        JToken jToken = new JToken(token,null,null);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(jToken);
        }catch (DisabledAccountException e){
            if("token error".equals(e.getMessage())){
                throw new ServiceException(ResponseCode.TOKEN_EXPIRED.msg,e,false,false);
            }
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }
}
