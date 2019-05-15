package cn.jorian.jorianframework.config.shiro;

import cn.jorian.jorianframework.common.realm.UserRealm;
import cn.jorian.jorianframework.config.jwt.JwtFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 09:38
 * @Description:
 */
@Configuration
public class ShiroConfig {
    @Autowired
    ShiroService shiroService;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/");
        //过滤链
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        //其余需要拦截
        filterChainDefinitionMap = shiroService.getFilterChainDefinitionMap();
        filterChainDefinitionMap.put("static/**", "anon");//此处放行静态文件
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");//放行swagger
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("perms",new JwtFilter());//是否带token的jwt
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;

    }

//1.
    @Bean
    public SecurityManager newSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.newRealm());
        return securityManager;
}
//2.
    @Bean
    public Realm newRealm() {
        UserRealm userRealm =new UserRealm();
        userRealm.setCredentialsMatcher(new CredentialsMatcher());
        return userRealm;
    }

}
