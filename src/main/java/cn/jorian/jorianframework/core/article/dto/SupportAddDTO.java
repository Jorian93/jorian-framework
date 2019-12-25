package cn.jorian.jorianframework.core.article.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:35
 * @Description:
 */
@Data
public class SupportAddDTO {

 @NotBlank(message = "父一级的id不能为空")
 private String pid;

 @NotBlank(message = "点赞者的id不能为空")
 private String supportUserId;

}
