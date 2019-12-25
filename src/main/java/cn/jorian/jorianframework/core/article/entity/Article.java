package cn.jorian.jorianframework.core.article.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author jorian
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("article_details")
@Document(indexName = "blog", type = "article")
public class Article extends BaseModel {

    private String author;

    /**
     *  1技术文章 , 2散文随笔
     */

    private Integer type;
    /**
     *  0.草稿  1,发布
     */
    private Integer status;

    /**
     * 文章标题
     */
    private String title;

    private String content;
    /**
     * 文章摘要
     */
    @TableField("contentShort")
    private String contentShort;

    private String uri;


    /**
     * 图片
     */
    private String images;

    private Integer score;

    @TableField("readCount")
    private Integer readCount;

    /**
     * 平台
     */
    @TableField(exist = false)
    private List<String> plateforms;


}
