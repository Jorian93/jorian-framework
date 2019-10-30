package cn.jorian.jorianframework.common.utils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;



/**
 * @Author: jorian
 * @Date: 2019/4/18 15:02
 * @Description:
 */
public  class EncryptPasswordTool {
    /**
     * 密码加密
     * @param username
     * @param password
     * @param hashIterations
     * @return
     */
    public static String ENCRYPT_MD5(String username,String password,int hashIterations){
        String algorithmName ="MD5";
        String MD5Password = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(username), hashIterations).toHex();
        return MD5Password;
    }
    //
}
