package cn.jorian.jorianframework.config.shiro;

import cn.jorian.jorianframework.config.jwt.JwtFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: jorian
 * @Date: 2019/4/17 09:38
 * @Description:
 */
@Configuration
public class ShiroConfig {
    @Autowired
    ShiroService shiroService;

    //不加这个注解不生效，具体不详
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/");

        //其余需要拦截
        //过滤链
        Map<String, String> filterChainDefinitionMap = shiroService.getFilterChainDefinitionMap();
        //此处放行静态文件
        filterChainDefinitionMap.put("static/**", "anon");
        filterChainDefinitionMap.put("/logout","logout");
        //放行swagger
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        //放行登录
        /*filterChainDefinitionMap.put("/account", "anon");*/
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        /*filterChainDefinitionMap.put("/**", "authc");*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();

        //是否带token的jwt
        filterMap.put("perms",new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;

    }

    //UserRealm
    @Bean
    public UserRealm myShiroRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(new CredentialsMatcher());
        return userRealm;
    }

    //SecurityManager
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.myShiroRealm());
        return securityManager;
    }


}
