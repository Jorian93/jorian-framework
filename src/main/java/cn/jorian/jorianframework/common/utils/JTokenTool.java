package cn.jorian.jorianframework.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: jorian
 * @Date: 2019/4/19 22:10
 * @Description:
 */
public class JTokenTool {
    @Autowired
    private org.springframework.data.redis.core.RedisTemplate RedisTemplate;

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
     * 获得token中的指定KEY值信息
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
