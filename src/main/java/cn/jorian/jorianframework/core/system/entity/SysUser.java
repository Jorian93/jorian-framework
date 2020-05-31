package cn.jorian.jorianframework.core.system.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;

import cn.jorian.jorianframework.common.model.BaseModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:56
 * @Description:
 */
@Data
@Accessors(chain = true)
public class SysUser extends BaseModel {

    private String nickname;
    private String username;
    private String password;
    private Integer sex;
    private Integer status;
    private String company;
    private String phone;
    private String email;
    private String avatar;

    @TableField(exist = false)
    List<SysRole> roles;
}
