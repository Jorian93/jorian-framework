package cn.jorian.jorianframework.core.account.service;


import cn.jorian.jorianframework.core.account.dto.LoginDTO;
import cn.jorian.jorianframework.core.account.dto.RestPasswordDTO;
import cn.jorian.jorianframework.core.account.dto.Router;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Auther: jorian
 * @Date: 2019/4/18 14:06
 * @Description:
 */
public interface AccountService extends IService<SysUser> {
    String login(LoginDTO loginDTO);

    SysUser getCurrentUser();

    List<Router> getCurrentUserResource();

    void resetPassword(RestPasswordDTO resetPasswordDTO);
}
