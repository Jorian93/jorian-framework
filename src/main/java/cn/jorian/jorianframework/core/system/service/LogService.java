package cn.jorian.jorianframework.core.system.service;

import cn.jorian.jorianframework.core.system.dto.LogFindDTO;
import cn.jorian.jorianframework.core.system.dto.UserFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysLog;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jorian
 * @since 2019-05-07
 */
public interface LogService extends IService<SysLog> {
    IPage<SysLog> getList(LogFindDTO logFindDTO);
}
