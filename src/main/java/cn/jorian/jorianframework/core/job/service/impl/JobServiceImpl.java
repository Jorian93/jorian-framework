package cn.jorian.jorianframework.core.job.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.core.job.SpringBeanJob;
import cn.jorian.jorianframework.core.job.dto.JobAddDTO;
import cn.jorian.jorianframework.core.job.dto.JobFindDTO;
import cn.jorian.jorianframework.core.job.entity.Job;
import cn.jorian.jorianframework.core.job.mapper.JobMapper;
import cn.jorian.jorianframework.core.job.service.JobService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jorian
 */
@Service
@Slf4j
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {


	@Autowired
	private Scheduler scheduler;
	@Autowired
	private ApplicationContext applicationContext;

	private static final String JOB_DATA_KEY = "JOB_DATA_KEY";

	@Override
	public IPage<Job> getList(JobFindDTO jobFindDTO) {
		Job job =new Job();
		BeanUtils.copyProperties(jobFindDTO, job);
		QueryWrapper<Job> queryWrapper =new QueryWrapper<>();
		if(!StringUtils.isEmpty(jobFindDTO.getJobName())){
			queryWrapper.eq("jobName",jobFindDTO.getJobName());
		}
		queryWrapper.orderByDesc(jobFindDTO.getSort());
		IPage<Job> pagedata = this.page(new Page<>(jobFindDTO.getPage(),jobFindDTO.getLimit()),queryWrapper);
		return pagedata;
	}

	@Override
	public void updateJob(Job job) {
		if(job==null){
			throw new ServiceException("不能保存空的资源");
		}
		Job findJob = this.getById(job.getId());
		if(findJob ==null) {
			throw new ServiceException("更新的资源不存在");
		}
		findJob = new Job();
		BeanUtils.copyProperties(job,findJob);
		checkJobModel(findJob);

		String jobName = findJob.getJobName();
		JobKey jobKey = JobKey.jobKey(jobName);
		// 任务详情
		JobDetail jobDetail = JobBuilder.newJob(SpringBeanJob.class).storeDurably()
				.withDescription(findJob.getDescription()).withIdentity(jobKey).build();
		jobDetail.getJobDataMap().put(JOB_DATA_KEY, findJob);
		// cron表达式
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(findJob.getCron());
		//cron触发器设置
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
				.withIdentity(jobName)
				.withSchedule(cronScheduleBuilder)
				.forJob(jobKey)
				.build();
		try {
			boolean exists = scheduler.checkExists(jobKey);
			if (!exists) {
				//报错
				throw  new ServiceException("任务出现碎片，请重新配置",200);
			}
			//销毁任务
			if(findJob.getStatus() == 0){
				scheduler.pauseJob(jobKey);
				scheduler.unscheduleJob(new TriggerKey(jobName));
				scheduler.deleteJob(jobKey);
			}
			//重启任务
			if(findJob.getStatus() == 1){
				scheduler.rescheduleJob(new TriggerKey(jobName), cronTrigger);
				scheduler.addJob(jobDetail, true);
			}
			//更新任务
			findJob.setUpdateTime(LocalDateTime.now());
			this.updateById(findJob);

		} catch (SchedulerException e) {
			log.error("修改job异常", e);
		}


	}

	@Override
	public void saveJob(JobAddDTO jobAddDTO) {

		Job findJob = this.getOne(new QueryWrapper<Job>().eq("jobName", jobAddDTO.getJobName()));
		if (findJob != null) {
			throw new IllegalArgumentException(jobAddDTO.getJobName() + "任务已存在");
		}
		findJob = new Job();
		BeanUtils.copyProperties(jobAddDTO,findJob);
		checkJobModel(findJob);

		String name = findJob.getJobName();
		JobKey jobKey = JobKey.jobKey(name);
		//任务详情
		JobDetail jobDetail = JobBuilder.newJob(SpringBeanJob.class)
				.storeDurably()
				.withDescription(findJob.getDescription())
				.withIdentity(jobKey)
				.build();

		jobDetail.getJobDataMap().put(JOB_DATA_KEY, findJob);
		// cron触发器配置
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(findJob.getCron());
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
				.withIdentity(name)
				.withSchedule(cronScheduleBuilder)
				.forJob(jobKey)
				.build();

		try {
			boolean exists = scheduler.checkExists(jobKey);
			if (exists) {
				scheduler.rescheduleJob(new TriggerKey(name), cronTrigger);
				scheduler.addJob(jobDetail, true);
			} else {
				scheduler.scheduleJob(jobDetail, cronTrigger);
			}
			//启动
			findJob.setStatus(1);
			findJob.setCreateTime(LocalDateTime.now());
			findJob.setUpdateTime(LocalDateTime.now());
			this.save(findJob);
			log.info("系统定时任务启动："+findJob.getJobName());
		} catch (SchedulerException e) {
			log.error("新增或修改job异常", e);
		}
	}

	private void checkJobModel(Job job) {
		String springBeanName = job.getSpringBeanName();
		boolean flag = applicationContext.containsBean(springBeanName);
		if (!flag) {
			throw new IllegalArgumentException("bean：" + springBeanName + "不存在，bean名如userServiceImpl,首字母小写");
		}
		Object object = applicationContext.getBean(springBeanName);
		Class<?> clazz = object.getClass();
		if (AopUtils.isAopProxy(object)) {
			clazz = clazz.getSuperclass();
		}
		String methodName = job.getMethodName();
		Method[] methods = clazz.getDeclaredMethods();

		Set<String> names = new HashSet<>();
		Arrays.asList(methods).forEach(m -> {
			Class<?>[] classes = m.getParameterTypes();
			if (classes.length == 0) {
				names.add(m.getName());
			}
		});

		if (names.size() == 0) {
			throw new IllegalArgumentException("该bean没有无参方法");
		}

		if (!names.contains(methodName)) {
			throw new IllegalArgumentException("未找到无参方法" + methodName + ",该bean所有方法名为：" + names);
		}
	}

	public void doJob(JobDataMap jobDataMap) {
		Job job =  (Job)jobDataMap.get(JOB_DATA_KEY);

		String beanName = job.getSpringBeanName();
		String methodName = job.getMethodName();
		Object object = applicationContext.getBean(beanName);

		try {
			log.info("quartz:bean：{}，方法名：{}", beanName, methodName);
			Method method = object.getClass().getDeclaredMethod(methodName);
			method.invoke(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除job
	 *
	 * @throws SchedulerException
	 */
	@Override
	public void deleteJob(String id) throws SchedulerException {
		if(id==null){
			throw new ServiceException("请输入要删除的任务id");
		}
		Job findjob = this.getById(id);

		if(findjob == null) {
			throw new ServiceException("要删除的任务不存在");
		}
		if (findjob.getIsSysJob() != null && findjob.getIsSysJob()) {
			throw new IllegalArgumentException("该job是系统任务，不能删除，因为此job是在代码里初始化的，删除该类job请先确保相关代码已经去除");
		}

		String jobName = findjob.getJobName();
		JobKey jobKey = JobKey.jobKey(jobName);

		scheduler.pauseJob(jobKey);
		scheduler.unscheduleJob(new TriggerKey(jobName));
		scheduler.deleteJob(jobKey);
        //此处删除
		this.removeById(findjob.getId());
		/*findjob.setStatus(0);
		this.updateById(findjob);*/
	}

}
