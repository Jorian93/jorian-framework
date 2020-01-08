package cn.jorian.jorianframework.core.system.dto;

import cn.jorian.jorianframework.core.system.entity.SysRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:35
 * @Description:
 */
@Data
public class UserAddDTO {
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{3,15}$",message = "用户名应为以字母开头，大小写字母及数字组成的4-16位字符！")
    private String username;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$",message = "密码应为大小写字母及数字组成的6-10位字符！")
    private String password;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    private String company;

    private Integer status;

    private Integer sex;

    @Size(min = 1, message = "请至少选择一个角色")
    private List<SysRole> roles;
}
