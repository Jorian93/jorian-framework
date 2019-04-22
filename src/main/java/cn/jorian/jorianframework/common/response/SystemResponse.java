package cn.jorian.jorianframework.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "系统响应")
@Getter
@Setter
public class SystemResponse implements Serializable {

    @ApiModelProperty(value = "响应码")
    private  Integer code;
    @ApiModelProperty(value = "响应消息")
    private  String msg;
    @ApiModelProperty(value = "响应数据")
    private  Object data;

    //操作类
    public  SystemResponse(ResponseCode responseCode){
        this.code = responseCode.code;
        this.msg = responseCode.msg;
    }
    public  SystemResponse(ResponseCode responseCode,Object data){
        this.code = responseCode.code;
        this.msg = responseCode.msg;
        this.data = data;
    }



}
