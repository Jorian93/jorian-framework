package cn.jorian.jorianframework.core.article.dto;

import lombok.Data;

/**
 * @Author: jorian
 * @Date: 2019/4/25 17:14
 * @Description:
 */
@Data
public class CommontUpdateDTO {

    /**
     * 评论的id
     */
    private String  id;

    /**
     * 评论点赞数
     */
    private Long support;


}
