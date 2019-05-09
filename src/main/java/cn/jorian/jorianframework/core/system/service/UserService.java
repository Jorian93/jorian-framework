package cn.jorian.jorianframework.core.system.service;

import cn.jorian.jorianframework.core.system.dto.UserAddDTO;
import cn.jorian.jorianframework.core.system.dto.UserFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

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

    IPage<SysUser> getList(UserFindDTO userFindDTO);

    List<SysUser> getTree();

    Set<String> getUserPermissions(String username);
}
