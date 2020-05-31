package cn.jorian.jorianframework.config.jwt;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.data.redis.core.RedisTemplate;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.RedisTool;
import cn.jorian.jorianframework.common.utils.ResponseTool;
import cn.jorian.jorianframework.common.utils.ToolJWT;
import cn.jorian.jorianframework.core.account.service.impl.ExecuteLoginService;

/**
 * @Author: jorian
 * @Date: 2019/4/17 17:01
 * @Description:
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    RedisTemplate redisTemplate;

    /**
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("【===已进入JWT_filter===】");
        HttpServletResponse response1 = (HttpServletResponse)response;
        //请求不携带token视为无效
        if(!this.isLoginAttempt(request,response)){
            ResponseTool.write(response1, ResponseCode.PERMISSIN_FAIL.code, ResponseCode.PERMISSIN_FAIL.msg);
            //如果是mvc项目，此处重定向到登录页，如果是restful,直接抛出异常或者返回响应，交由前端处理
            return false;
        }
        // 通过执行登录统一验证请求
        try {
            this.executeLogin(request,response);
        }catch (ServiceException e){
            ResponseTool.write(response1,e.getStatus(),e.getMessage());
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
        //无凭证示没有登录或者登录失败，返回登录提示
        if (null == subject.getPrincipal()) {
            ResponseTool.write(response1, ResponseCode.NO_SIGN_IN_FAIL);
        }else{
            ResponseTool.write(response1, ResponseCode.PERMISSIN_FAIL);
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
        return  request1.getHeader(new JToken().TOKEN_KEY);
    }
}
