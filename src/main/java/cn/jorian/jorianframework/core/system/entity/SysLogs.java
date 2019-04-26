package cn.jorian.jorianframework.core.system.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysLogs extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private Integer userId;

    /**
     * 模块名
     */
    private String module;

    /**
     * 成功失败
     */
    private Integer flag;

    /**
     * 备注
     */
    private String remark;


}
