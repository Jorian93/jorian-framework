package cn.jorian.jorianframework.core.system.dto;

import cn.jorian.jorianframework.core.system.entity.SysRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Auther: jorian
 * @Date: 2019/4/18 15:35
 * @Description:
 */
@Data
public class UserAddDTO {
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^(\\w){4,16}$",message = "用户名应为[A-Za-z0-9_]组成的4-16位字符！")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(\\w){6,18}$",message = "密码应为[A-Za-z0-9_]组成的6-18位字符！")
    private String password;

    @NotNull(message = "手机号不能为空")
    private Integer phone;

    @NotNull(message = "状态标识不能为空")
    private Integer status;

    @Size(min = 1, message = "请至少选择一个角色")
    private List<SysRole> roles;
}
