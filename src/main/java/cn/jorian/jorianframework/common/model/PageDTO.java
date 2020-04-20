package cn.jorian.jorianframework.common.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * author:jorian
 */
@Data
public abstract class PageDTO {


    @ApiModelProperty(value="当前页码",name="page",example="1")
    private Integer page = 1;
    @ApiModelProperty(value="分页大小",name="limit",example="20")
    private Integer limit = 10;

    private String sort = "createTime";

}
