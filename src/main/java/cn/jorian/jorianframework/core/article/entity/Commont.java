package cn.jorian.jorianframework.core.article.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * commont
 * </p>
 *
 * @author jorian
 * @since 2019-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Commont extends BaseModel {

    /**
     * 评论对应回答的id
     */
    private String aid;
    /**
     * 评论人的id
     */
    @TableField("comUserId")
    private String comUserId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论点赞数
     */
    private Long support;

    /**
     * 是否可以点赞
     */
    @TableField(exist = false)
    private boolean canLiko;

    /**
     * 评论人的信息
     */
    @TableField(exist = false)
    SysUser sysUser;


}
