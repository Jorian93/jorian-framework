package cn.jorian.jorianframework.config.quartz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author jorian
 */
@Configuration
public class QuartzConfig {

	public static final String KEY = "applicationContextSchedulerContextKey";

	@Bean("adminQuartzScheduler")
	public SchedulerFactoryBean quartzScheduler(DataSource dataSource) {
		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

		try {
			quartzScheduler.setQuartzProperties(
					PropertiesLoaderUtils.loadProperties(new ClassPathResource("quartz.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		quartzScheduler.setDataSource(dataSource);
		quartzScheduler.setOverwriteExistingJobs(true);
		quartzScheduler.setApplicationContextSchedulerContextKey(KEY);
		quartzScheduler.setStartupDelay(10);

		return quartzScheduler;
	}

//	@Autowired
//	private JobService jobService;
//	@Autowired
//	private TaskExecutor taskExecutor;
//
//	/**
//	 * 初始化一个定时删除日志的任务
//	 */
//	@PostConstruct
//	public void initDeleteLogsJob() {
//		taskExecutor.execute(() -> {
//			Job jobModel = new Job();
//			jobModel.setJobName("delete-logs-job");
//			jobModel.setCron("0 0 0 * * ?");
//			jobModel.setDescription("定时删除三个月前日志");
//			jobModel.setSpringBeanName("sysLogServiceImpl");
//			jobModel.setMethodName("deleteLogs");
//			jobModel.setIsSysJob(true);
//			jobModel.setStatus(1);
//
//			jobService.saveJob(jobModel);
//		});
//	}

}
