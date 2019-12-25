package cn.jorian.jorianframework.core.article.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:35
 * @Description:
 */
@Data
public class CommontAddDTO {

 @NotBlank(message = "对应的回答id不能为空")
 private String aid;

 @NotBlank(message = "评论人不能为空")
 private String comUserId;

 @NotBlank(message = "评论内容不能为空")
 private String content;


}
