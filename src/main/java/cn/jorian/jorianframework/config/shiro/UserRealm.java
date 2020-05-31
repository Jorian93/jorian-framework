package cn.jorian.jorianframework.config.shiro;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.model.LoginUser;
import cn.jorian.jorianframework.common.model.RedisDB;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.ResponseTool;
import cn.jorian.jorianframework.common.utils.ToolJWT;
import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import cn.jorian.jorianframework.core.system.service.UserService;

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
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JToken;
    }

    /**
     * 认证
     * 
     * @param token 用户token subject.login(token)
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("【已经入Shiro认证...】");
        SysUser sysUser = new SysUser();
        JToken jToken = (JToken) token;
        String jwt = jToken.getJwt();
        String username = jToken.getUsername();

        /**
         * 1.如果账号密码登录，则jwt必然为空，代表用户首次登入，给jwt指向一个新的令牌 2.如果jwt登录，则账号密码必然为空，代表普通的登入认证
         */
        if (jwt == null) {
            // 首次登陆账号密码验证
            if (StringUtils.isEmpty(username)) {
               
                throw new ServiceException(ResponseCode.SIGN_IN_USERNAME_PASSWORD_EMPTY.msg);
            }
            try {
                sysUser = userService.getOne(
                        new QueryWrapper<SysUser>().eq("username", username).select("id,username,status,password"));
            } catch (ServiceException e) {
                throw new DisabledAccountException(e.getMessage());
            }
            if (sysUser == null) {
                throw new DisabledAccountException(ResponseCode.SIGN_IN_USERNAME_PASSWORD_FAIL.msg);
            }
            // 非启用状态
            if (sysUser.getStatus() == ResponseCode.USER_ISLOCKED.code) {
                throw new DisabledAccountException(ResponseCode.USER_ISLOCKED.msg);
            }
            // 生成新的jwt
            jwt = new ToolJWT().generateJWT(sysUser.getId(), sysUser.getUsername());

        } else {
            // 走token验证
            String dbkey = RedisDB.getDBKey(RedisDB.TABLE_USER) + ToolJWT.get(jwt, "uid");
           try{
            LoginUser cacheUser = (LoginUser)redisTemplate.opsForValue().get(dbkey);  
            if(cacheUser == null){
                throw new ServiceException(ResponseCode.TOKEN_EXPIRED);
            }   
            log.debug("cache user:{}",cacheUser);
            long curTime = System.currentTimeMillis();
            // 刷新缓存里的user
            if(JToken.REFRESH_TIME > 0){
                if(cacheUser.getExpiredAt()-curTime < JToken.REFRESH_TIME){
                    cacheUser.setExpiredAt(curTime + JToken.EXPIRE_TIME*1000);//重设过期时间点
                    redisTemplate.opsForValue().set(dbkey, cacheUser,JToken.EXPIRE_TIME, TimeUnit.SECONDS); //刷新token过期时间
                    // TODO: 重新签发jwt？
                }
            }
           }catch(Exception e){
                throw new DisabledAccountException(ResponseCode.TOKEN_AUTHENTICATION_FAIL.msg);
               // throw new ServiceException(ResponseCode.TOKEN_AUTHENTICATION_FAIL);
           }

        }

        jToken.setJwt(jwt);
        JToken jt = new JToken();
        BeanUtils.copyProperties(jToken, jt);
        // 4.对用户信息进行封装
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                // principal(用户身份)
                jt,
                // hashedCredentials(已经加密的密码)
                sysUser.getPassword(),
                // realm name
                this.getName());
        log.debug("【===Shiro认证完成===】");
        return info;
    }

    /**
     * 授权
     * 
     * @param principalCollection 用户凭证
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("【===已进入Shiro授权===】");
        JToken jToken = new JToken();
        BeanUtils.copyProperties(principalCollection.getPrimaryPrincipal(), jToken);
        String username = jToken.getUsername() != null ? jToken.getUsername()
                : ToolJWT.get(jToken.getJwt(), "username");
        if (username != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> pSet = userService.getUserPermissions(username);
            info.setStringPermissions(pSet);
            log.info("【===Shiro授权完成===】");
            return info;
        } else {
            throw new DisabledAccountException("用户信息异常，请重新登录！");
        }

    }

    /**
     * 重写方法,清除当前用户的的 授权缓存
     * 
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     * 
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的 认证缓存 和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
