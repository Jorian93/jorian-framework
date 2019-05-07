package cn.jorian.jorianframework.core.system.service.impl;


import cn.jorian.jorianframework.core.system.entity.SysLog;
import cn.jorian.jorianframework.core.system.mapper.LogMapper;
import cn.jorian.jorianframework.core.system.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jorian
 * @since 2019-05-07
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SysLog> implements SysLogService {

}
