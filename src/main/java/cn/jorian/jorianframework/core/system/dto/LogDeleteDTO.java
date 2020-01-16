package cn.jorian.jorianframework.core.system.dto;

import cn.jorian.jorianframework.common.model.PageDTO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: jorian
 * @Date: 2019/4/25 17:14
 * @Description:
 */
@Data
public class LogDeleteDTO {

    /**
     * 开始时间
     */
    private LocalDateTime startDateTime;
    /**
     * 结束时间
     */
    private LocalDateTime endDateTime;

}
