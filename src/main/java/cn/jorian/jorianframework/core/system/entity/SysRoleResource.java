package cn.jorian.jorianframework.core.system.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author jorian
 * @since 2019-04-24
 */
@Data
@Accessors(chain = true)
public class SysRoleResource implements Serializable {
    private static final long serialVersionUID = 177030063138338860L;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @TableField("roleId")
    private String roleId;

    @TableField("resourceId")
    private String resourceId;


}
