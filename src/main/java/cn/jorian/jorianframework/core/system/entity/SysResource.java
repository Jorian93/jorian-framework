package cn.jorian.jorianframework.core.system.entity;

import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

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
public class SysResource extends BaseModel {

    private String pid;

    private String name;

    private String title;

    private String icon;

    private String path;

    private String redirect;

    private String component;

    private Integer type;

    private String permission;

    private Integer sort;

    @TableField("isVerify")
    private Boolean isVerify;

    private Boolean hidden;

    @TableField(exist =false)
    List<SysResource> children;


}
