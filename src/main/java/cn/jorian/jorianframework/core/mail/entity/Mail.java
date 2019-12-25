package cn.jorian.jorianframework.core.mail.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jorian
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_mail")
public class Mail extends BaseModel {
    /**
     * 发送人
     */
    @TableField("userId")
    private String userId;

    @TableField(exist = false)
    private String toUsers;
    /**
     * 标题
     */
    private String subject;

    /**
     * 正文
     */
    private String content;


}
