package cn.jorian.jorianframework.core.article.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:35
 * @Description:
 */
@Data
public class ArticleAddDTO {


    @NotBlank(message = "作者不能为空")
    private String author;

    private Integer type;


    private Integer status;

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空")
    private String title;

    @NotBlank(message = "文章内容不能为空")
    private String content;


    private String images;

    private String contentShort;

    @NotBlank(message = "文章链接不能为空")
    private String uri;

    /**
     * 平台id
     */
    private List<String> plateforms;


    private Integer score;

}
