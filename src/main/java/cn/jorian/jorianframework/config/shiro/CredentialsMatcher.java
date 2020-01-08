package cn.jorian.jorianframework.config.shiro;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.EncryptPasswordTool;
import cn.jorian.jorianframework.common.utils.JTokenTool;
import cn.jorian.jorianframework.config.jwt.JToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Author: jorian
 * @Date: 2019/4/19 14:51
 * @Description:
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher{

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
            JToken jToken = (JToken) token;
           //获得一个数据库中的密文密码,此处是从认证取到
            Object accountCredentials = getCredentials(info);
            if(jToken.getUsername()!=null){
                //明文密码变密文
                Object tokenCredentials = EncryptPasswordTool.ENCRYPT_MD5(jToken.getUsername(),jToken.getPassword());
                if(!accountCredentials.equals(tokenCredentials)){
                    throw new DisabledAccountException(ResponseCode.SIGN_IN_USERNAME_PASSWORD_FAIL.msg);
                }
            }else{
                try{
                    return JTokenTool.verify(jToken.getToken());
                }catch (Exception e){
                    throw new ServiceException(e.getMessage(),e);
                }
            }
        return true;
    }


}
