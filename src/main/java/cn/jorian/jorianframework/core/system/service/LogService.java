package cn.jorian.jorianframework.core.system.service;

import cn.jorian.jorianframework.core.system.dto.LogDeleteDTO;
import cn.jorian.jorianframework.core.system.dto.LogFindDTO;
import cn.jorian.jorianframework.core.system.dto.UserFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysLog;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /**
     * 日志按日期时间删除
     * @param logDeleteDTO 删除条件模板
     */
    void deleteByDate(LogDeleteDTO logDeleteDTO);
}
