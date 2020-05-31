package cn.jorian.jorianframework.core.job.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author jorian
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_job")
public class Job extends BaseModel {


	private String jobName;

	private String description;

	private String cron;


	private String springBeanName;


	private String methodName;


	private Boolean isSysJob;

	/**
	 * 1.启动 0.停止
	 */
	private int status;

}
