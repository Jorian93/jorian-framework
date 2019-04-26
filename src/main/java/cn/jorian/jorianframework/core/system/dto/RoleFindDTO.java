package cn.jorian.jorianframework.core.system.dto;

import cn.jorian.jorianframework.common.model.PageDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: jorian
 * @Date: 2019/4/25 17:14
 * @Description:
 */
@Data
public class RoleFindDTO extends PageDTO {

    private String name;

}
