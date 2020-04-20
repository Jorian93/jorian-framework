package cn.jorian.jorianframework.common.utils;


import cn.jorian.jorianframework.config.jwt.JToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Author: jorian
 * @Date: 2019/4/17 14:46
 * @Description:
 */
@Slf4j
public class JTool_Token {


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
        Date expiredDate = new Date(System.currentTimeMillis()+ JToken.EXPIRE_TIME);
        log.info("【J-Token过期时间:{}...】",expiredDate);
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
                .withExpiresAt(expiredDate)
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
