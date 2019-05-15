package cn.jorian.jorianframework.core.system.service.impl;


import cn.jorian.jorianframework.core.system.dto.LogFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysLog;
import cn.jorian.jorianframework.core.system.mapper.LogMapper;
import cn.jorian.jorianframework.core.system.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jorian
 * @since 2019-05-07
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SysLog> implements LogService {

    @Override
    public IPage<SysLog> getList(LogFindDTO logFindDTO) {
        SysLog sysLog =new SysLog();
        BeanUtils.copyProperties(logFindDTO,sysLog);
        QueryWrapper<SysLog> queryWrapper =new QueryWrapper<>();
        if(!StringUtils.isEmpty(logFindDTO.getUsername())){
            queryWrapper.eq("username",logFindDTO.getUsername());
        }
        queryWrapper.orderByDesc(logFindDTO.getSort());
        IPage<SysLog> pagedata = this.page(new Page<>(logFindDTO.getPage(),logFindDTO.getLimit()),queryWrapper);
        return pagedata;


    }
}
