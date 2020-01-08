package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.core.system.entity.SysRoleResource;
import cn.jorian.jorianframework.core.system.mapper.RoleResourceMapper;
import cn.jorian.jorianframework.core.system.service.RoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: jorian
 * @Date: 2019/4/18 16:11
 * @Description:
 */
@Service
@Transactional
public class RoleResourceServcieImpl extends ServiceImpl<RoleResourceMapper, SysRoleResource> implements RoleResourceService {

}
