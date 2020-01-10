package cn.jorian.jorianframework.core.account.service;


import cn.jorian.jorianframework.core.account.dto.RestPasswordDTO;
import cn.jorian.jorianframework.core.account.dto.Router;
import cn.jorian.jorianframework.core.account.dto.UsernamePasswordDTO;
import cn.jorian.jorianframework.core.system.entity.SysResource;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: jorian
 * @Date: 2019/4/18 14:06
 * @Description:
 */
public interface AccountService extends IService<SysUser> {
    /**
     * 账户登录
     * @param usernamePasswordDTO
     * @return
     */
    String login(UsernamePasswordDTO usernamePasswordDTO);

    /**
     * 获取当前用户
     * @return
     */
    SysUser getCurrentUser();

    /**
     * 获取当前用户的前端资源
     * @return
     */
    List<Router> getCurrentUserResource();

    /**
     * 重置用户密码
     * @param resetPasswordDTO
     */
    void resetPassword(RestPasswordDTO resetPasswordDTO);

    /**
     * 微信登录
     * @param code
     * @return
     */
    String wxlogin(String code);

    List addChildrenToParrent(List<SysResource> operationList, List<SysResource> allResource);

    List<Router> toRouterTree(List<SysResource> resourceList);
}
