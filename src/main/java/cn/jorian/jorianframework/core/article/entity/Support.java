package cn.jorian.jorianframework.core.article.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jorian
 * @since 2019-10-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Support extends BaseModel {

    /**
     * 主体id,评论id/回答id
     */
    private String pid;

    @TableField("supportUserId")
    private String supportUserId;


}
