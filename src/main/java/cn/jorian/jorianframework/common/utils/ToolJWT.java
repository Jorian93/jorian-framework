package cn.jorian.jorianframework.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: jorian
 * @Date: 2019/4/17 14:46
 * @Description:
 */
@Slf4j
@Component
public class ToolJWT {

    /**
     * 1.jwt只是一个web凭证，就像一张证明，一张纸 2.jwt的所有的token，和信息都是静态的数据，仅仅用来表征一个jwt
     * 3.过期时间只是jwt的一个属性，类似的，还有各种各样的属性，比如这个jwt谁签发的
     * 
     * EXPIRE_TIME = -1,代表永不过期
     */
    /**
     * SECRET
     */
    public static String SECRET = "123456";

 
    public static String TOKEN_KEY = "J-Token";

 
    public static long EXPIRE_TIME = -1;


    public static long REFRESH_TIME = -1;
    /**
     * @param sECRET the sECRET to set
     */
    @Value("${token.jwt.secret}")
    public  void setSECRET(String sECRET) {
        SECRET = sECRET;
    }
    /**
     * @param tOKEN_KEY the tOKEN_KEY to set
     */
    @Value("${token.name}")
    public  void setTOKEN_KEY(String tOKEN_KEY) {
        TOKEN_KEY = tOKEN_KEY;
    }
    /**
     * @param eXPIRE_TIME the eXPIRE_TIME to set
     */
    @Value("${token.expired}")
    public  void setEXPIRE_TIME(int eXPIRE_TIME) {
        EXPIRE_TIME = eXPIRE_TIME;
    }

    /**
     * @param rEFRESH_TIME the rEFRESH_TIME to set
     */
    @Value("${token.refresh}")
    public  void setREFRESH_TIME(int rEFRESH_TIME) {
        REFRESH_TIME = rEFRESH_TIME;
    }
    /**
     * token生成工具
     ** 
     * @param uid      用户id
     * @param username 用户名
     * @param secret   秘钥
     * @return
     */
    public String generateJWT(String uid, String username) {

        Algorithm algorithm = null;
        try {
            // 加密盐
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Builder b = JWT.create().withClaim("uid", uid);// 在jwt里边存一个用户id
        // .withClaim("username", username);
        if (EXPIRE_TIME > 0) {
            Date expiredDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            b.withExpiresAt(expiredDate);
        }
        return b.sign(algorithm);
    }

    /**
     * jwt
     * 
     * @param jwt
     * @return
     * @throws UnsupportedEncodingException
     * @throws IllegalArgumentException
     */
    public static boolean verify(String jwt) throws IllegalArgumentException, UnsupportedEncodingException {

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        try {
            DecodedJWT dJwt = verifier.verify(jwt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 从token中解析出指定的key，例如username，password
     */
    public static String get(String jwt, String key) {
        // 先校验jwt是否过期
        try {
            DecodedJWT djwt = JWT.decode(jwt);
            return djwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
