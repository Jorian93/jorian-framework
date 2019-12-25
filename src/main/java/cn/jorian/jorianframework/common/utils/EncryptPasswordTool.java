package cn.jorian.jorianframework.common.utils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


/**
 * @Auther: jorian
 * @Date: 2019/4/18 15:02
 * @Description: 加密工具
 */
public  class EncryptPasswordTool {
    //MD5 ENCRYPT
    public static String ENCRYPT_MD5(String salt,String password,int hashIterations){
        String algorithmName ="MD5";
        String MD5Password = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(salt), hashIterations).toHex();
        return MD5Password;
    }
    //MD5 DEENCRYPT
    public static String DEENCRYPT_MD5(String username,String password,int hashIterations){
        String algorithmName ="MD5";
        String MD5Password = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(username), hashIterations).toHex();
        return MD5Password;
    }
    //
}
