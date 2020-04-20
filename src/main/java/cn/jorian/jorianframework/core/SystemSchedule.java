package cn.jorian.jorianframework.core;

import cn.jorian.jorianframework.core.job.dto.JobAddDTO;
import cn.jorian.jorianframework.core.job.entity.Job;
import cn.jorian.jorianframework.core.job.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.PostConstruct;

/**
 * @author: jorian
 * @date: 2019/12/21 18:57
 * @description: this is  description for the class
 */
@Slf4j
@Configuration
public class SystemSchedule {

    @Autowired
    private JobService jobService;
    @Autowired
    private TaskExecutor taskExecutor;
    /**
     * 初始化一个定时删除日志的任务，随系统启动
     */

    @PostConstruct
    public void initDeleteLogsJob() {
        taskExecutor.execute(() -> {
            Job jobModel = new Job();
            jobModel.setJobName("系统任务-删除2星期前日志");
            jobModel.setCron("0 0 0 * * ?");
            jobModel.setDescription("定时删除2个星期前日志");
            jobModel.setSpringBeanName("logServiceImpl");
            jobModel.setMethodName("deleteLogs2weekFromNow");
            jobModel.setIsSysJob(true);
            jobModel.setStatus(1);
            JobAddDTO jobAddDTO = new JobAddDTO();
            BeanUtils.copyProperties(jobModel,jobAddDTO);
            jobService.saveJob(jobAddDTO);
        });

    }
}
