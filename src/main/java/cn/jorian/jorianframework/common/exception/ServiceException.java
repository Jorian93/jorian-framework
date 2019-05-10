package cn.jorian.jorianframework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 09:30
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException implements Serializable{
     private  String message;
     private  Integer status;
     private Exception e;

    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable e){
        super(e);
    }
    public ServiceException(String message,Integer status){
        this.message = message;
        this.status = status;
    }
    public ServiceException(String message,Throwable e){
        super(message,e);
    }
    public ServiceException(String message, Throwable e,boolean enableSuppression,boolean writableStackTrace){
        super(message,e,enableSuppression,writableStackTrace);
    }



}
