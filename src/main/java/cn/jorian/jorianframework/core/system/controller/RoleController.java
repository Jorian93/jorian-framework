package cn.jorian.jorianframework.core.system.controller;

import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.SystemResponse;
import cn.jorian.jorianframework.core.system.dto.RoleAddDTO;
import cn.jorian.jorianframework.core.system.dto.RoleFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysRole;
import cn.jorian.jorianframework.core.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 09:24
 * @Description:
 */
@RestController
@Api(tags = "角色管理")
public class RoleController {
    @Autowired
    RoleService roleService;

    @ApiOperation("新增角色")
    @RequestMapping(value="role",method = RequestMethod.POST)
    public SystemResponse roleAdd(@RequestBody @Validated @ApiParam(value = "角色基础信息") RoleAddDTO roleAddDTO){
        roleService.add(roleAddDTO);
        return new SystemResponse(ResponseCode.SUCCESS);
    }
    @ApiOperation("删除角色")
    @RequestMapping(value="role/{id}",method = RequestMethod.DELETE)
    public SystemResponse roleDelete(@PathVariable("id") @ApiParam(value = "角色id")String id){
        roleService.delete(id);
        return new SystemResponse(ResponseCode.SUCCESS);
    }
    @ApiOperation("更新角色")
    @RequestMapping(value="role",method = RequestMethod.PUT)
    public SystemResponse roleUpdate(@RequestBody @Validated @ApiParam(value = "角色更新数据") SysRole sysRole){
        roleService.update(sysRole);
        return new SystemResponse(ResponseCode.SUCCESS);
    }
    @ApiOperation("角色列表查询")
    @RequestMapping(value="role/list",method = RequestMethod.GET)
    public SystemResponse roleList(RoleFindDTO roleFindDTO){
        return new SystemResponse(ResponseCode.SUCCESS, roleService.getList(roleFindDTO));
    }
    @ApiOperation("角色树查询")
    @RequestMapping(value="role/tree",method = RequestMethod.GET)
    public SystemResponse roleTree(){
        return new SystemResponse(ResponseCode.SUCCESS,roleService.getTree());
    }
}
