package cn.jorian.jorianframework.common.realm;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.JTokenTool;
import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import cn.jorian.jorianframework.core.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @Author: jorian
 * @Date: 2019/4/17 09:50
 * @Description:
 */
@Service
public class UserRealm extends AuthorizingRealm {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JToken;
    }


    /**
     * 认证
     * @param token 用户token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("====Shiro认证执行======");
        JToken jToken =  (JToken) token;
        String tk = jToken.getToken();
        String username = jToken.getUsername()!=null?jToken.getUsername(): JTokenTool.get(jToken.getToken(),"username");
        SysUser sysUser = new SysUser();
        if(StringUtils.isEmpty(username)){
                throw new ServiceException(ResponseCode.SIGN_IN_USERNAME_PASSWORD_EMPTY.msg);
        }
        try {
                sysUser = userService.getOne(new QueryWrapper<SysUser>()
                        .eq("username",username)
                        .select("id,username,status,password"));
        }catch (ServiceException e){
            throw new DisabledAccountException(e.getMessage());
        }
        if(sysUser==null){
            throw new DisabledAccountException(ResponseCode.SIGN_IN_USERNAME_PASSWORD_FAIL.msg);
        }
        if(sysUser.getStatus()!=1){
            throw new DisabledAccountException(ResponseCode.USER_ISLOCKED.msg);
        }
        //生成token，此时的jToken是明文账号密码+token
        if(tk==null) {
            tk= JTokenTool.generateToken(sysUser.getId(),sysUser.getUsername(),sysUser.getPassword());
        }
        jToken.setToken(tk);
        //4.对用户信息进行封装 (principal(用户身份),hashedCredentials(已经加密的密码),realm name)
        return new SimpleAuthenticationInfo(jToken, sysUser.getPassword(), this.getName());
    }

    /**
     * 授权
     * @param principalCollection  用户凭证
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("======Shiro授权执行=====");
        JToken jToken = new JToken();
        BeanUtils.copyProperties(principalCollection.getPrimaryPrincipal(),jToken);
        String username = jToken.getUsername()!=null?jToken.getUsername(): JTokenTool.get(jToken.getToken(),"username");
        if(username!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> pSet = userService.getUserPermissions(username);
            info.setStringPermissions(pSet);
                return info;
        }else{
            throw new DisabledAccountException("用户信息异常，请重新登录！");
        }
    }
//    public void clearAuthByUserId(String uid,Boolean author, Boolean out){
//        //获取所有session
//        Cache<Object, Object> cache = cacheManager
//                .getCache(MyRealm.class.getName()+".authorizationCache");
//        cache.remove(uid);
//    }
//
//    public void clearAuthByUserIdCollection(List<String> userList, Boolean author, Boolean out){
//        Cache<Object, Object> cache = cacheManager
//                .getCache(MyRealm.class.getName()+".authorizationCache");
//        userList.forEach(cache::remove);
//    }


}
