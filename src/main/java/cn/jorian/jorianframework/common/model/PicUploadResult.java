package cn.jorian.jorianframework.common.model;

import lombok.Data;

/**
 * @Author: jorian
 * @Date: 2019/5/1 09:36
 * @Description:
 */
@Data
public class PicUploadResult {

 private int error;
 private  String height;
 private  String width;
 private String Url;
 private  String msg;

}
