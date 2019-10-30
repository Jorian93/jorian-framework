package cn.jorian.jorianframework.core.mail.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
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
@Builder
@Accessors(chain = true)
@TableName("t_mail_to")
public class MailTo extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableField("mailId")
    private String mailId;

    @TableField("toUser")
    private String toUser;

    /**
     * 1成功，0失败
     */
    private Integer status;


}
