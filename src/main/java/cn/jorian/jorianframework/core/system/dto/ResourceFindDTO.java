package cn.jorian.jorianframework.core.system.dto;

import cn.jorian.jorianframework.common.model.PageDTO;
import lombok.Data;

/**
 * @Author: jorian
 * @Date: 2019/4/25 17:14
 * @Description:
 */
@Data
public class ResourceFindDTO extends PageDTO {

    private String pid;
    private String name;
    private Boolean isVerify;

}
