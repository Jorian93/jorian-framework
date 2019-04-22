package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.core.system.entity.SysResource;
import cn.jorian.jorianframework.core.system.mapper.ResourceMapper;
import cn.jorian.jorianframework.core.system.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: jorian
 * @Date: 2019/4/18 16:11
 * @Description:
 */
@Service
@Transactional
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, SysResource> implements ResourceService {
}
