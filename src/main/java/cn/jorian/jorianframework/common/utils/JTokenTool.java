package cn.jorian.jorianframework.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.omg.CORBA.ServerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Author: jorian
 * @Date: 2019/4/19 22:10
 * @Description:
 */
public class JTokenTool {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private  StringRedisTemplate stringRedisTemplate;
    private  final Logger log = LoggerFactory.getLogger("JTokenTool");

    /**
     * 签名过期时间一周
     */
    private static final long EXPIRE_TIME = 7*12*3600*1000;

    /**
     * token校验
     * @param token
     * @return
     */
    public static boolean verify(ServerRequest request,String token) {

        // 声明一个redisTemplate
        RedisTemplate redisTemplate = new RedisTemplate();
        // 获取容器
        HttpServletRequest req = (HttpServletRequest) request;
        ServletContext sc = req.getSession().getServletContext();
        XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);

        // 从容器中获取redisTemplate
        if(cxt != null && cxt.getBean("redisTemplate") != null && redisTemplate == null) {
            redisTemplate = (RedisTemplate) cxt.getBean("redisTemplate");
        }
         String redisToken = (String)redisTemplate.opsForValue().get("J-Token");

        return redisToken.equals(token);

    }


    /**
     * 获得token中的指定KEY值信息
     */
    public static String get(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成登录签名
     * @param uid
     * @param username
     * @param secret
     * @return
     */
    public static String generateToken(String uid, String username, String secret){
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm = null;
        try {
            //secret加密盐
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
