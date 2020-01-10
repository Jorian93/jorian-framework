package cn.jorian.jorianframework.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Author: jorian
 * @Date: 2019/4/17 14:46
 * @Description:
 */
public class JTool_Token {

    private static final long EXPIRE_TIME = 7*24*3600;
    @Autowired
    private org.springframework.data.redis.core.RedisTemplate RedisTemplate;

    /**
     * token生成工具
     **@param uid 用户id
     * @param username 用户名
     * @param secret 秘钥
     * @return
     */
    public String generateToken(String uid,String username,String secret){
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm = null;
        try {
            //加密盐
            algorithm = Algorithm.HMAC256(secret);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return JWT.create()
                .withClaim("uid",uid)
                .withClaim("username",username)
                .withExpiresAt(date)
                .sign(algorithm);
    }


    /**
     * token校验
     * @param token
     * @return
     */
    public static boolean verify(String token) {

        //用token登录的情况，有token，在认证时已经取出用户名在数据库查了，认证通过
            /*String tk = token;
            System.out.println("1"+tk);
            String tok = stringRedisTemplate.opsForValue().get("J-Token");
            System.out.println("2"+tok);
            if (tok == null){
                throw new ServiceException(ResponseCode.TOKEN_EXPIRED.msg);
            }
            if(!tok.equals(tk)){
                throw new ServiceException(ResponseCode.TOKEN_AUTHENTICATION_FAIL.msg);
            }*/
        return true;
    }


    /**
     * 从token中解析出指定的key，例如username，password
     */
    public static String get(String token,String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }




}
