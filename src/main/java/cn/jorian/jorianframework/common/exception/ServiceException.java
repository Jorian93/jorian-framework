package cn.jorian.jorianframework.common.exception;

import cn.jorian.jorianframework.common.response.ResponseCode;

import javax.xml.ws.Service;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 09:30
 * @Description:
 */
public class ServiceException extends RuntimeException{

    public ServiceException(){
        super();
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable cause){
        super(cause);
    }
    public ServiceException(String message,Throwable e){
        super(message,e);
    }
    public ServiceException(String message, Throwable cause,boolean enableSuppression,boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }



}
