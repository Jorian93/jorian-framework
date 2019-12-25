package cn.jorian.jorianframework.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "系统响应")
@Getter
@Setter
public class ResponseResult implements Serializable {


    @ApiModelProperty(value = "响应码")
    private  Integer code;
    @ApiModelProperty(value = "响应消息")
    private  String msg;
    @ApiModelProperty(value = "响应数据")
    private  Object data;
    @ApiModelProperty(value = "响应时间")
    private LocalDateTime responseTime = LocalDateTime.now();

    /****
     * 实例初始化的方式构造返回结果
     * @param responseCode
     */
    public ResponseResult(ResponseCode responseCode){
        this.code = responseCode.code;
        this.msg = responseCode.msg;
    }
    public ResponseResult(ResponseCode responseCode, Object data){
        this.code = responseCode.code;
        this.msg = responseCode.msg;
        this.data = data;
    }
    public ResponseResult(Integer code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /****
     * 操作方法的方式返回
     * @param responseCode
     * @return
     */
    public  ResponseResult e(ResponseCode responseCode){
        return this.e(responseCode,null);
    }
    public  ResponseResult e(ResponseCode responseCode, Object data){
        return ResponseResult.builder()
                .code(responseCode.code)
                .msg(responseCode.msg)
                .data(data)
                .build();
    }


}
