package cn.jorian.jorianframework.core.system.service;

import cn.jorian.jorianframework.core.system.dto.UserAddDTO;
import cn.jorian.jorianframework.core.system.dto.UserFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @Author: jorian
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

    /**
     * 获取用户列表
     * @param userFindDTO
     * @return
     */
    IPage<SysUser> getList(UserFindDTO userFindDTO);

    /**
     * 获取用户树
     * @return
     */
    List<SysUser> getTree();

    /**
     * 获取用户权限
     * @param username
     * @return
     */
    Set<String> getUserPermissions(String username);

    /**
     * 根据用户名模糊查询
     * @param name
     * @return
     */
    List<SysUser> serarch(String name);
}
