package cn.jorian.jorianframework.core.job.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author jorian
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Job extends BaseModel {

	@TableField("jobName")
	private String jobName;

	@TableField("description")
	private String description;

	private String cron;

	@TableField("springBeanName")
	private String springBeanName;

	@TableField("methodName")
	private String methodName;

	@TableField("isSysJob")
	private Boolean isSysJob;

	/**
	 * 1.启动 0.停止
	 */
	private int status;

}
