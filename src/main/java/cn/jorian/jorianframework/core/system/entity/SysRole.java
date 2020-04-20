package cn.jorian.jorianframework.core.system.entity;


import cn.jorian.jorianframework.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:56
 * @Description:
 */
@Data
@Accessors(chain = true)
public class SysRole extends BaseModel {

    private String name;
    private String description;

    @TableField(exist = false) //数据库没有此字段
    private List<SysResource> resources;
}
