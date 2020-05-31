package cn.jorian.jorianframework.common.aspect;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.jorian.jorianframework.common.annotation.Jlog;
import cn.jorian.jorianframework.common.utils.ToolHttpInfo;
import cn.jorian.jorianframework.common.utils.ToolJWT;
import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.core.account.dto.RestPasswordDTO;
import cn.jorian.jorianframework.core.account.dto.UsernamePasswordDTO;
import cn.jorian.jorianframework.core.system.dto.UserAddDTO;
import cn.jorian.jorianframework.core.system.entity.SysLog;
import cn.jorian.jorianframework.core.system.service.LogService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jorian
 * @version 2018/4/27/17:19 description：日志处理切面
 */
@Aspect
@Component
@Slf4j
public class JlogAspect {

    private final LogService logService;

    @Autowired
    public JlogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("@annotation(cn.jorian.jorianframework.common.annotation.Jlog)")
    public void jlog() {
    }

    @AfterReturning("jlog()")
    public void after(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        PrincipalCollection principalCollection = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipals() != null) {
            principalCollection = subject.getPrincipals();
        }
        SysLog sysLog = new SysLog();
        // 获取动作Action释义
        sysLog.setActionName(getMethodSysLogsAnnotationValue(joinPoint));
        // 获取IP
        sysLog.setIp(ToolHttpInfo.getClientIp(request));
        sysLog.setAjax(ToolHttpInfo.ajax(request) ? 1 : 0);
        sysLog.setApi(request.getRequestURI());
        String s = this.paramFilter(joinPoint.getArgs());
        // 根据系统需求自定义
        sysLog.setParams(s.length() > 500 ? "数据过大，不给予记录" : s);
        sysLog.setHttpMethod(request.getMethod());
        sysLog.setClassMethod(
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "()");
        // 判断身份是否为空
        if (principalCollection != null) {
            JToken jToken = new JToken();
            BeanUtils.copyProperties(principalCollection.getPrimaryPrincipal(), jToken);
            String username = jToken.getUsername() != null ? jToken.getUsername()
                    : ToolJWT.get(jToken.getJwt(), "username");
            // 从token中获取
            sysLog.setUsername(username);
        } else {
            sysLog.setUsername("游客");
        }
        // 打上时间
        sysLog.setCreateTime(LocalDateTime.now());
        // 保存日志到db
        logService.save(sysLog);

    }

    private String getMethodSysLogsAnnotationValue(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(Jlog.class)) {
            // 获取方法上注解中表明的权限
            Jlog JLog = method.getAnnotation(Jlog.class);
            return JLog.value();
        }
        return "未知";
    }

    private String paramFilter(Object[] params) {
        // 判断是否含有密码敏感数据，将含有密码的地方替换为*
        final String filterString = "******";
        if (params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof UsernamePasswordDTO) {
                    UsernamePasswordDTO sign = (UsernamePasswordDTO) params[i];
                    sign.setPassword(filterString);
                    params[i] = sign;
                }
                if (params[i] instanceof UserAddDTO) {
                    UserAddDTO userAddDTO = (UserAddDTO) params[i];
                    userAddDTO.setPassword(filterString);
                    params[i] = userAddDTO;
                }
                if (params[i] instanceof RestPasswordDTO) {
                    RestPasswordDTO resetPasswordDTO = (RestPasswordDTO) params[i];
                    resetPasswordDTO.setPassword(filterString);
                    params[i] = resetPasswordDTO;
                }
            }
        }
        return JSON.toJSONString(params);
    }

}
