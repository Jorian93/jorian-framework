package cn.jorian.jorianframework.common.utils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


/**
 * @Author: jorian
 * @Date: 2019/4/18 15:02
 * @Description: 密码加密工具
 * */
public  class JTool_EncryptPassword {

    /**
     *
     * @param salt 盐值 以账号作为盐值
     * @param password 密码原值
     * @return
     */
    public static String ENCRYPT_MD5(String salt,String password){
        //加密方法
        String algorithmName ="MD5";
        //加密次数
        int hashIterations = 1024;

        String MD5Password = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(salt), hashIterations).toHex();
        return MD5Password;
    }

}
