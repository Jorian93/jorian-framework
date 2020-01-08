package cn.jorian.jorianframework.core.job;

import cn.jorian.jorianframework.config.quartz.QuartzConfig;
import cn.jorian.jorianframework.core.job.service.JobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author jorian
 */
public class SpringBeanJob extends QuartzJobBean {
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext()
					.get(QuartzConfig.KEY);
			JobService jobService = applicationContext.getBean(JobService.class);
			jobService.doJob(context.getJobDetail().getJobDataMap());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
