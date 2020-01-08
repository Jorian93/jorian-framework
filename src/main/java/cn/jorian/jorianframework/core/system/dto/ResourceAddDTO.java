package cn.jorian.jorianframework.core.system.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: jorian
 * @Date: 2019/4/18 15:35
 * @Description:
 */
@Data
public class ResourceAddDTO {
    @NotBlank(message = "父级不能为空")
    private String pid;

    @NotBlank(message = "组件名不能为空")
    private String name;

    @NotBlank(message = "资源名不能为空")
    private String title;

    private String icon;

    @NotBlank(message = "资源路由不能为空")
    private String path;

    @NotBlank(message = "组件地址不能为空")
    private String component;

    private String redirect;

    private Integer type;

    @NotBlank(message = "权限表达式不能为空")
    private String permission;
    @NotNull
    private Integer sort;

    private Boolean isVerify;

    private Boolean hidden;
}
