package cn.jorian.jorianframework.config.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;


/**
 * @Auther: jorian
 * @Date: 2019/4/17 14:33
 * @Description:
 */
@NoArgsConstructor
@Data
public class JToken implements AuthenticationToken {
    public static  final String TOKEN_KEY = "J-Token";
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
