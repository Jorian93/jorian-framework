package cn.jorian.jorianframework.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @Auther: jorian
 * @Date: 2019/4/19 22:10
 * @Description:
 */
public class JTokenUtil {

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
