package cn.jorian.jorianframework.common.utils;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 14:46
 * @Description:
 */
public class CreateJwtToken {
    private static final long EXPIRE_TIME = 7*24*3600;

    public String generateToken(String uid,String username,String secret){
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm = null;//加密算法加密后的盐
        try {
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

}
