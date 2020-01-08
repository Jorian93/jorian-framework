package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.core.system.entity.SysUserRole;
import cn.jorian.jorianframework.core.system.mapper.UserRoleMapper;
import cn.jorian.jorianframework.core.system.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: jorian
 * @Date: 2019/4/18 16:02
 * @Description:
 */
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, SysUserRole> implements UserRoleService {

}
