package cn.jorian.jorianframework.config.shiro;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.EncryptPassword;
import cn.jorian.jorianframework.common.utils.JTokenUtil;
import cn.jorian.jorianframework.config.jwt.JToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Auther: jorian
 * @Date: 2019/4/19 14:51
 * @Description:
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
            JToken jToken = (JToken) token;
           //获得一个数据库中的密文密码,此处是从认证取到
            Object accountCredentials = getCredentials(info);
            if(jToken.getUsername()!=null){
                Object tokenCredentials = EncryptPassword.ENCRYPT_MD5(jToken.getUsername(),jToken.getPassword(),2);//明文密码变密文
                if(!accountCredentials.equals(tokenCredentials)){
                    throw new DisabledAccountException(ResponseCode.SIGN_IN_USERNAME_PASSWORD_FAIL.msg);
                }
            }else{
                try{
                    return JTokenUtil.verify(jToken.getToken());
                }catch (Exception e){
                    throw new ServiceException(e.getMessage(),e);
                }
            }
        return true;
    }


}
