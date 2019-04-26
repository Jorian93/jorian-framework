package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.core.system.entity.SysLogs;
import cn.jorian.jorianframework.core.system.mapper.LogsMapper;
import cn.jorian.jorianframework.core.system.service.SysLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jorian
 * @since 2019-04-24
 */
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, SysLogs> implements SysLogsService {

}
