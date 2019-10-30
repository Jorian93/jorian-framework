package cn.jorian.jorianframework.core.file.entity;

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
@TableName("file_info")
public class MyFile extends BaseModel {

    @TableField("contentType")
    private String contentType;

    private Integer size;

    /**
     * 物理路径
     */
    private String path;

    private String url;

    private Integer type;


}
