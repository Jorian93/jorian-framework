package cn.jorian.jorianframework.common.response;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public enum  ResponseCode {
 SUCCESS(200,"操作成功"),
 FAIL(-1,"操作失败"),
 SIGN_IN_SUCCESS(200,"登录成功"),
 SIGN_IN_FAIL(207,"登录失败"),
 SIGN_IN_USERNAME_PASSWORD_FAIL(200,"用户名或密码错误"),
 USER_ISLOCKED(206,"用户已被锁定"),
 SIGN_IN_USERNAME_PASSWORD_EMPTY(201,"用户名或密码为空"),
 SIGN_OUT_SUCCESS(202,"登出成功"),
 PERMISSIN_FAIL(203,"没有足够的权限,请重新登录"),
 TOKEN_EXPIRED(204,"凭证过期，请重新登录"),
 TOKEN_AUTHENTICATION_FAIL(205,"凭证校验失败"),
    ;

 public Integer code;
 public String msg;

 public static List<ResponseMessage> getMessageList(){
    List<ResponseMessage> messageList = new ArrayList<>();
    for(ResponseCode responseCode: ResponseCode.values()){
    messageList.add(new ResponseMessageBuilder()
           .code(responseCode.code)
           .message(responseCode.msg)
           .build());
    }
    return messageList;
 }


}
