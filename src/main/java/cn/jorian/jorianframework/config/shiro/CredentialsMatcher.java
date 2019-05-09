package cn.jorian.jorianframework.config.shiro;

import cn.jorian.jorianframework.common.utils.EncryptPassword;
import cn.jorian.jorianframework.common.utils.JTokenUtil;
import cn.jorian.jorianframework.config.jwt.JToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Auther: jorian
 * @Date: 2019/4/19 14:51
 * @Description:
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
            JToken jToken = (JToken) token;
            Object accountCredentials = getCredentials(info);//获得一个数据库中的密文密码,此处是从认证取到
            if(jToken.getUsername()!=null){
                Object tokenCredentials = EncryptPassword.ENCRYPT_MD5(jToken.getUsername(),jToken.getPassword(),2);//明文密码变密文
                if(!accountCredentials.equals(tokenCredentials)){
                    throw new DisabledAccountException("密码不正确！");
                }
            }else{
                //用token登录的情况，有token，在认证时已经取出用户名在数据库查了，认证通过
                return true;
            }
        return true;
    }


}
