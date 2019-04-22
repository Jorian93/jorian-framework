package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.core.system.entity.SysRole;
import cn.jorian.jorianframework.core.system.mapper.RoleMapper;
import cn.jorian.jorianframework.core.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 11:09
 * @Description:
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {

}
