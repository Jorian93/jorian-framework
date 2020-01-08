package cn.jorian.jorianframework.core.article.dto;

import cn.jorian.jorianframework.common.model.PageDTO;
import lombok.Data;

/**
 * @Author: jorian
 * @Date: 2019/4/25 17:14
 * @Description:
 */
@Data
public class ArticleFindDTO extends PageDTO {

    private String title;
    private Integer type;
    private String author;
    private Integer status;
    private Integer score;

}
