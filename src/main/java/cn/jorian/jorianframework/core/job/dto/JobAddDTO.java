package cn.jorian.jorianframework.core.job.dto;

import cn.jorian.jorianframework.common.model.PageDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jorian
 * @Date: 2019/4/25 17:14
 * @Description:
 */
@Data
public class JobAddDTO extends PageDTO {

    @NotBlank(message="任务名不得为空")
    private String jobName;


    private String description;

    @NotBlank(message="cron表达式不能为空")
    private String cron;

    @NotBlank(message="服务类不得为空")
    private String springBeanName;

    @NotBlank(message="方法不得为空")
    private String methodName;

    private Boolean isSysJob;

    private int status;

}
