package cn.jorian.jorianframework.core.system.dto;

import cn.jorian.jorianframework.common.model.PageDTO;
import lombok.Data;

/**
 * @Author: jorian
 * @Date: 2019/4/25 17:14
 * @Description:
 */
@Data
public class UserFindDTO extends PageDTO {

    private String nickname;
    private String username;
    private String company;
    private Boolean status;

}
