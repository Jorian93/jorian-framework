package cn.jorian.jorianframework.common.model;

import lombok.Data;

/*
 * @Description: 
 * @Author: jorian
 * @Date: 2020-05-31 16:17:42
 */ 
@Data
public class LoginUser extends BaseModel {
    
    /**
     *
     */
    private static final long serialVersionUID = 7170483876405935666L;
    /**
     * 过期时间点
     */
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

}
