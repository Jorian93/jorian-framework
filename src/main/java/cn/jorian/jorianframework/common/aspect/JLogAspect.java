package cn.jorian.jorianframework.common.aspect;


import cn.jorian.jorianframework.common.annotation.JLog;
import cn.jorian.jorianframework.common.factory.JLogFactory;
import cn.jorian.jorianframework.common.factory.JLogService;
import cn.jorian.jorianframework.common.utils.JTool_HttpInfo;
import cn.jorian.jorianframework.common.utils.JTool_Token;
import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.config.rabbitmq.RabbitMQProviderConfig;
import cn.jorian.jorianframework.core.account.dto.RestPasswordDTO;
import cn.jorian.jorianframework.core.account.dto.UsernamePasswordDTO;
import cn.jorian.jorianframework.core.system.dto.UserAddDTO;
import cn.jorian.jorianframework.core.system.entity.SysLog;
import cn.jorian.jorianframework.core.system.service.LogService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;


/**
 * @author jorian
 * @version 2018/4/27/17:19
 * description：日志处理切面
 */
@Aspect
@Component
@Slf4j
public class JLogAspect {
    @Autowired
    AmqpTemplate amqpTemplate;

    private final LogService logService;

    @Autowired
    public JLogAspect(LogService logService) {
        this.logService = logService;
    }


    @Pointcut("@annotation(cn.jorian.jorianframework.common.annotation.JLog)")
    public void jlog(){}

    @AfterReturning("jlog()")
    public void after(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        PrincipalCollection principalCollection = null;
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipals()!=null){
            principalCollection = subject.getPrincipals();
        }
        SysLog sysLog = new SysLog();
        //获取动作Action释义
        sysLog.setActionName(getMethodSysLogsAnnotationValue(joinPoint));
        //获取IP
        sysLog.setIp(JTool_HttpInfo.getClientIp(request));
        sysLog.setAjax(JTool_HttpInfo.ajax(request) ? 1 : 0);
        sysLog.setApi(request.getRequestURI());
        String s = this.paramFilter(joinPoint.getArgs());
        //根据系统需求自定义
        sysLog.setParams(s.length()>500 ? "数据过大，不给予记录" : s);
        sysLog.setHttpMethod(request.getMethod());
        sysLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName()+"()");
        //判断身份是否为空
        if(principalCollection!=null){
            JToken jToken = new JToken();
            BeanUtils.copyProperties(principalCollection.getPrimaryPrincipal(),jToken);
            String username = jToken.getUsername()!=null?jToken.getUsername(): JTool_Token.get(jToken.getToken(),"username");
            //从token中获取
            sysLog.setUsername(username);
        }else{
            sysLog.setUsername("游客");
        }
        //打上时间
        sysLog.setCreateTime(LocalDateTime.now());
        //保存日志到mysql
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        try{
            //JLogService jLogService = JLogFactory.generateLogService(JLogFactory.SAVE_TO_MYSQL);
            //jLogService.save(sysLog,JLogFactory.SAVE_TO_MYSQL);
            String ROUTING_KEY = "log.mysql";
            this.amqpTemplate.convertAndSend(RabbitMQProviderConfig.TOPIC_EXCHANGE, ROUTING_KEY, sysLog );
            log.info("【已发送消息至路由{} --- routingKey:{}】", RabbitMQProviderConfig.TOPIC_EXCHANGE,ROUTING_KEY);
        }catch (Exception e){
            logService.save(sysLog);
        }

    }

    private String getMethodSysLogsAnnotationValue(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(JLog.class)){
            //获取方法上注解中表明的权限
            JLog JLog = method.getAnnotation(JLog.class);
            return JLog.value();
        }
        return "未知";
    }

    private String paramFilter(Object[] params){
        //判断是否含有密码敏感数据，将含有密码的地方替换为*
        final String filterString = "******";
        if(params.length>0){
            for (int i = 0; i < params.length; i++) {
                if(params[i] instanceof UsernamePasswordDTO){
                    UsernamePasswordDTO sign = (UsernamePasswordDTO) params[i];
                    sign.setPassword(filterString);
                    params[i] = sign;
                }
                if(params[i] instanceof UserAddDTO){
                    UserAddDTO userAddDTO = (UserAddDTO) params[i];
                    userAddDTO.setPassword(filterString);
                    params[i] = userAddDTO;
                }
                if(params[i] instanceof RestPasswordDTO){
                    RestPasswordDTO resetPasswordDTO = (RestPasswordDTO) params[i];
                    resetPasswordDTO.setPassword(filterString);
                    params[i] = resetPasswordDTO;
                }
            }
        }
        return JSON.toJSONString(params);
    }


}
