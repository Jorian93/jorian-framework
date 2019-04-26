package cn.jorian.jorianframework.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 09:18
 * @Description:
 */
@Data
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 177030063138338860L;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    @TableField("createTime")
    LocalDateTime createTime;
    @TableField("updateTime")
    LocalDateTime updateTime;
}
