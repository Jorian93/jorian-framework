package cn.jorian.jorianframework.config.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Value;


/**
 * @Author: jorian
 * @Date: 2019/4/17 14:33
 * @Description:
 */
@NoArgsConstructor
@Data
public class JToken implements AuthenticationToken {

    @Value("jwt.token.name")
    public static  final String TOKEN_KEY = "J-Token";
    @Value("jwt.token.expired")
    public static final long EXPIRE_TIME = 7*24*3600;

    public String token;
    public String username;
    public String password;

    public JToken(String token, String username, String password) {
        this.token = token;
        this.username = username;
        this.password = password;
    }
    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
