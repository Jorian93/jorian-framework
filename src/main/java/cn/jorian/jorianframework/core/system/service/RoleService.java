package cn.jorian.jorianframework.core.system.service;

import cn.jorian.jorianframework.core.system.dto.RoleAddDTO;
import cn.jorian.jorianframework.core.system.dto.RoleFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @Author: jorian
 * @Date: 2019/4/17 10:50
 * @Description:
 */
public interface RoleService extends IService<SysRole> {

    void add(RoleAddDTO roleAddDTO);

    void delete(String id);
    void update(SysRole sysResource);

    Object getList(RoleFindDTO roleFindDTO);

    Object getTree();
}
