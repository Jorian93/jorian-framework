package cn.jorian.jorianframework.config.shiro;

import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.common.utils.EncryptPassword;
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
            Object tokenCredentials = EncryptPassword.ENCRYPT_MD5(jToken.getUsername(),jToken.getPassword(),2);
            Object accountCredentials = getCredentials(info);//获得一个数据库加密后的密码
            // System.out.println(tokenCredentials);
            //System.out.println(accountCredentials);
            if(!accountCredentials.equals(tokenCredentials)){
                 throw new DisabledAccountException("密码不正确！");
            }
        return true;
    }


}
