package cn.jorian.jorianframework.core.system.dto;

import cn.jorian.jorianframework.core.system.entity.SysResource;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:35
 * @Description:
 */
@Data
public class RoleAddDTO {
    @NotBlank(message="角色名称不能为空")
    private String name;

    private String description;

    @Size(min = 1,message = "请至少选择一个权限资源")
    private List<SysResource> resources;
}
