package cn.jorian.jorianframework.core.system.dto;

import cn.jorian.jorianframework.common.model.PageDTO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: jorian
 * @Date: 2019/4/25 17:14
 * @Description:
 */
@Data
public class LogDeleteDTO extends PageDTO {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


}
