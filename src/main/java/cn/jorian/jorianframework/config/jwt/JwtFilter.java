package cn.jorian.jorianframework.config.jwt;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
import cn.jorian.jorianframework.config.rabbitmq.RabbitMQProviderConfig;
import cn.jorian.jorianframework.core.account.service.impl.ExecuteLoginService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: jorian
 * @Date: 2019/4/17 17:01
 * @Description:
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
     private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("【已进入JWT_filter...】");
        HttpServletResponse response1 = (HttpServletResponse)response;
        if(!this.isLoginAttempt(request,response)){
            this.writerResponse(response1, ResponseCode.PERMISSIN_FAIL.code, ResponseCode.PERMISSIN_FAIL.msg);
            //重定向到登录页
            return false;
        }
        try {
            this.executeLogin(request,response);
        }catch (ServiceException e){
            writerResponse(response1,e.getStatus(),e.getMessage());
            return false;
        }
        Subject subject = getSubject(request, response);
        //权限标识
        if(mappedValue != null ){
            String[] value = (String[])mappedValue;
            for (String permission : value) {
                if(permission==null || "".equals(permission.trim())){
                    continue;
                }
                if(subject.isPermitted(permission)){
                    return true;
                }
            }
        }
        //无凭证示没有登录，返回登录提示
        if (null == subject.getPrincipal()) {
            writerResponse(response1, ResponseCode.NO_SIGN_IN_FAIL.code, ResponseCode.NO_SIGN_IN_FAIL.msg);
        }else{
            writerResponse(response1, ResponseCode.PERMISSIN_FAIL.code, ResponseCode.PERMISSIN_FAIL.msg);
        }
        return false;
    }

    /**
     *是否携带token
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response){
        String token = this.getAuthzHeader(request);
        return token != null;
    }

    /**
     * 执行登录逻辑
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        return ExecuteLoginService.executeLogin(request);
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        return false;
    }


    @Override
    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest request1 = (HttpServletRequest) request;
        return  request1.getHeader("J-Token");
    }

    private void writerResponse(HttpServletResponse response,Integer status,String content){
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(ResponseResult.builder()
                    .code(status)
                    .msg(content)
                    .build()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
