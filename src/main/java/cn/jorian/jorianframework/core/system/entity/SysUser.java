package cn.jorian.jorianframework.core.system.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2019/4/17 08:50
 * @Description:
 */
@Data
@Accessors(chain = true)
public class SysUser extends BaseModel {

    String nickname;
    String username;
    String password;
    int sex;
    int status;
    String company;
    String phone;
    String email;
    String avatar;
    @TableField(exist = false)
    List<SysRole> roles;
}
