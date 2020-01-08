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
 * Quartz配置
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



}
