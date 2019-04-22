package cn.jorian.jorianframework.common.exception;

import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.SystemResponse;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SystemException {

    /**
     * shiro异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public SystemResponse shiroExceptionHandler(ShiroException e){
     e.printStackTrace();
     if(e instanceof AuthenticationException){
        return new SystemResponse(ResponseCode.SIGN_IN_USERNAME_PASSWORD_FAIL);
     }else if(e instanceof AuthorizationException){}
        return new SystemResponse(ResponseCode.PERMISSIN_FAIL);
    }


}
