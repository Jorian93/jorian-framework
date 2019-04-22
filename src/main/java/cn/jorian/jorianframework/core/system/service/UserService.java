package cn.jorian.jorianframework.core.system.service;

import cn.jorian.jorianframework.core.system.dto.UserAddDTO;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 10:09
 * @Description:
 */

public interface UserService  extends IService<SysUser> {

    /**
     * 新增
     */
    void add(UserAddDTO userAddDTO);
    /**
     * 删除
     */
    void delete(String id);
    /**
     * 更新
     */
    void update(SysUser sysUser);

    /**
     * 根据用户名查找对象
     */
    SysUser findUserByUsername(String username);
}
