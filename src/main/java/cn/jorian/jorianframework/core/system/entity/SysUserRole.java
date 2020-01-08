package cn.jorian.jorianframework.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:56
 * @Description:
 */
@Data
@Accessors(chain = true)
public class SysUserRole implements Serializable{
   private static final long serialVersionUID = 177030063138338860L;
   @TableId(value = "id", type = IdType.ID_WORKER_STR)
   private String id;
   private String uid;
   private String rid;
}
