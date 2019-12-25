package cn.jorian.jorianframework.core.system.service.impl;


import cn.jorian.jorianframework.core.system.dto.LogDeleteDTO;
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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void deleteByDate(LogDeleteDTO logDeleteDTO) {

        List<SysLog> logs = this.list(new QueryWrapper<SysLog>()
                .between("createTime",logDeleteDTO.getStartDateTime(),logDeleteDTO.getEndDateTime())
        );
        List<String > ids = new ArrayList<>();
        logs.forEach(item->{
            ids.add(item.getId());
        });
        this.deleteByIds(ids);
    }

    @Override
    public void deleteByIds(List<String > ids){
        this.removeByIds(ids);
    }

    public void deleteAllLogs(){
        System.out.println("删除所有日志");
    }
    public void deleteLogs2weekFromNow(){
        LocalDateTime nowTime = LocalDateTime.now();
        //2周前
        LocalDateTime targetTime = nowTime.minus(2L, ChronoUnit.WEEKS);

        List<SysLog> logs = this.list(new QueryWrapper<SysLog>().lt("createTime",targetTime));
        if(!logs.isEmpty()){
            logs.stream().forEach(log-> {
                this.removeById(log.getId());
            });
        }
    }

}
