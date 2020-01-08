package cn.jorian.jorianframework.core.article.dto;

import cn.jorian.jorianframework.common.model.PageDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:35
 * @Description:
 */
@Data
public class CommontFindDTO extends PageDTO {

 @NotBlank(message = "回答者id不能为空")
 private String aid;

 /**
  * 当前用户id
  */
 @NotBlank(message = "当前用户id不能为空")
 private String currUserId;

}
