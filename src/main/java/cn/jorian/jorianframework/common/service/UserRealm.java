package cn.jorian.jorianframework.common.service;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.model.Dict;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.CreateJwtToken;
import cn.jorian.jorianframework.common.utils.JTokenUtil;
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

/**
 * @Auther: jorian
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
        JToken jToken =  (JToken) token;
        String username = jToken.getUsername() != null?jToken.getUsername(): JTokenUtil.get(jToken.getToken(),"username");

        if(username==null){
            throw new ServiceException(ResponseCode.SIGN_IN_USERNAME_PASSWORD_EMPTY.msg);
        }
        SysUser findUser = userService.getOne(new QueryWrapper<SysUser>().eq("username",username));
        if(findUser==null){
            throw new AuthenticationException();
        }else if(findUser.getStatus()== Dict.USER_LOCK.key){
            throw new ServiceException("用户被锁定");
        }else{
         //4.对用户信息进行封装
           String tk = new CreateJwtToken().generateToken(findUser.getId(),findUser.getUsername(),findUser.getPassword());
           jToken.setToken(tk);
           return new SimpleAuthenticationInfo(jToken, //principal(用户身份)
                   findUser.getPassword(),//hashedCredentials(已经加密的密码)
                    this.getName());//realm name
        }
    }

    /**
     * 授权
     * @param principalCollection  用户凭证
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("Shiro授权执行");
        JToken jToken = new JToken();
        BeanUtils.copyProperties(principalCollection.getPrimaryPrincipal(),jToken);
        if(jToken.getUsername()!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            SysUser findUser = userService.findUserByUsername(jToken.getUsername());
            if(findUser!=null){
                if(findUser.getRoles()!=null){
                    findUser.getRoles().forEach(role->{
                        info.addRole(role.getName());
                        if(role.getResources()!=null){
                            role.getResources().forEach(v->{
                                info.addStringPermission("[system]");
                                /*if(!"".equals(v.getPermission().trim())){
                                    info.addStringPermission(v.getPermission());
                                }*/
                            });
                        }
                    });
                }
                return info;
            }
        }
        throw new DisabledAccountException("用户信息异常，请重新登录！");
    }
    /*public void clearAuthByUserId(String uid,Boolean author, Boolean out){
        //获取所有session
        Cache<Object, Object> cache = cacheManager
                .getCache(MyRealm.class.getName()+".authorizationCache");
        cache.remove(uid);
    }

    public void clearAuthByUserIdCollection(List<String> userList, Boolean author, Boolean out){
        Cache<Object, Object> cache = cacheManager
                .getCache(MyRealm.class.getName()+".authorizationCache");
        userList.forEach(cache::remove);
    }
*/

}
