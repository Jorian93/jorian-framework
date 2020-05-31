package cn.jorian.jorianframework.config.jwt;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Token entity, contails token base info
 * @Author: jorian
 * @Date: 2019/4/17 14:33
 * @Description:
 */
@NoArgsConstructor
@Data
@Component
public class JToken implements AuthenticationToken , Serializable {
   
    private static final long serialVersionUID = 4841961229561776698L;

    @Autowired
    RedisTemplate redisTemplate;

    
    public static String TOKEN_KEY = "J-Token";

    
    public static int EXPIRE_TIME = -1;

    
    public static int REFRESH_TIME = -1;
   
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
    
    private long expiredAt;
    /**
     * jwt
     */
    private String jwt;

    /**
     * username
     */
    private String username;
    /**
     * encryted password
     */
    private String password;

    public JToken(String jwt, String username, String password) {
        this.jwt = jwt;
        this.username = username;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return this.jwt;
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }
    

}
