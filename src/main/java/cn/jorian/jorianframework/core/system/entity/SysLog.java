package cn.jorian.jorianframework.core.system.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:56
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysLog extends BaseModel {

    private String username;

    private String nickname;

    private String ip;

    private Integer ajax;

    private String api;

    private String params;

    private String httpMethod;

    private String classMethod;

    private String actionName;



}
