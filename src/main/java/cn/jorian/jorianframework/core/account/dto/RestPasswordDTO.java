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
public class RestPasswordDTO  extends UsernamePasswordDTO{



    @NotBlank(message = "新密码不得为空")
    @Pattern(regexp = "^(\\w){6,18}$",message = "密码应为[A-Za-z0-9_]组成的6-18位字符！")
    String newPassword;
}
