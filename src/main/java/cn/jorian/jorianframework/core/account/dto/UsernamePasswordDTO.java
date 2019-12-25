package cn.jorian.jorianframework.core.account.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: jorian
 * @Date: 2019/4/19 09:20
 * @Description:
 */
@Data
public class UsernamePasswordDTO {

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{3,15}$",message = "用户名应为以字母开头，大小写字母及数字组成的4-16位字符！")
    private String username;

    @NotBlank(message = "密码不得为空")
    @Pattern(regexp = "^(\\w){6,18}$",message = "密码应为[A-Za-z0-9_]组成的6-18位字符！")
    String password;
}
