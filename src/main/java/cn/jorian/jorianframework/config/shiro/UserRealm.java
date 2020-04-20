package cn.jorian.jorianframework.config.shiro;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.JTool_Token;
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
     * @param token 用户token  subject.login(token)
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("【已经入Shiro认证...】");
        JToken jToken =  (JToken) token;

        String username = jToken.getUsername()!=null?jToken.getUsername(): JTool_Token.get(jToken.getToken(),"username");
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
        //非启用状态
        if(sysUser.getStatus()!=1){
            throw new DisabledAccountException(ResponseCode.USER_ISLOCKED.msg);
        }
        String tk = jToken.getToken();
        //生成token
        if(tk==null){
            tk= new JTool_Token().generateToken(sysUser.getId(),sysUser.getUsername(),sysUser.getPassword());
        }
        //此时的jToken是明文账号密码+token
        jToken.setToken(tk);
        //4.对用户信息进行封装
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                //principal(用户身份)
               jToken,
                //hashedCredentials(已经加密的密码)
               sysUser.getPassword(),
                //realm name
               this.getName()
        );
        log.info("【Shiro认证完成】");
        return info;
    }

    /**
     * 授权
     * @param principalCollection  用户凭证
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("【已进入Shiro授权...】");
        JToken jToken = new JToken();
        BeanUtils.copyProperties(principalCollection.getPrimaryPrincipal(),jToken);
        String username = jToken.getUsername()!=null?jToken.getUsername(): JTool_Token.get(jToken.getToken(),"username");
        if(username!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> pSet = userService.getUserPermissions(username);
            info.setStringPermissions(pSet);
            //System.out.println(info.getStringPermissions());
            log.info("【Shiro授权完成】");
            return info;
        }else{
            throw new DisabledAccountException("用户信息异常，请重新登录！");
        }

    }
    /**
     * 重写方法,清除当前用户的的 授权缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
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
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }


}
