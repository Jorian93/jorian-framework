package cn.jorian.jorianframework.core.job.service;

import cn.jorian.jorianframework.core.job.dto.JobAddDTO;
import cn.jorian.jorianframework.core.job.dto.JobFindDTO;
import cn.jorian.jorianframework.core.job.entity.Job;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;

/**
 * @author jorian
 */
public interface JobService extends IService<Job> {
	/**
	 * 定时任务列表查询
	 * @param logFindDTO 查询条件模板
	 * @return
	 */
	IPage<Job> getList(JobFindDTO logFindDTO);

	void saveJob(JobAddDTO jobAddDTO);
    void updateJob(Job job);
	void doJob(JobDataMap jobDataMap);
	void deleteJob(String id) throws SchedulerException;

}
