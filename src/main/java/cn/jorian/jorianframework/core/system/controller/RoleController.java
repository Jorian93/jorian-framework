package cn.jorian.jorianframework.core.system.controller;

import cn.jorian.jorianframework.common.annotation.JLog;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
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
 * @Author: jorian
 * @Date: 2019/4/17 09:24
 * @Description:
 */
@RestController
@Api(tags = "角色管理")
@RequestMapping("/system")
public class RoleController {
    @Autowired
    RoleService roleService;

    @ApiOperation("新增角色")
    @RequestMapping(value="/role/add",method = RequestMethod.POST)
    @JLog("新增角色")
    public ResponseResult roleAdd(@RequestBody @Validated @ApiParam(value = "角色基础信息") RoleAddDTO roleAddDTO){
        roleService.add(roleAddDTO);
        return new ResponseResult(ResponseCode.SUCCESS);
    }
    @ApiOperation("删除角色")
    @RequestMapping(value="/role/delete/{id}",method = RequestMethod.DELETE)
    @JLog("删除角色")
    public ResponseResult roleDelete(@PathVariable("id") @ApiParam(value = "角色id")String id){
        roleService.delete(id);
        return new ResponseResult(ResponseCode.SUCCESS);
    }
    @ApiOperation("更新角色")
    @RequestMapping(value="/role/update",method = RequestMethod.PUT)
    @JLog("更新角色")
    public ResponseResult roleUpdate(@RequestBody @Validated @ApiParam(value = "角色更新数据") SysRole sysRole){
        roleService.update(sysRole);
        return new ResponseResult(ResponseCode.SUCCESS);
    }
    @ApiOperation("角色列表查询")
    @RequestMapping(value="/role/list",method = RequestMethod.GET)
    @JLog("角色列表查询")
    public ResponseResult roleList(RoleFindDTO roleFindDTO){
        return new ResponseResult(ResponseCode.SUCCESS, roleService.getList(roleFindDTO));
    }
    @ApiOperation("角色树查询")
    @RequestMapping(value="/role/tree",method = RequestMethod.GET)
    public ResponseResult roleTree(){
        return new ResponseResult(ResponseCode.SUCCESS,roleService.getTree());
    }
}
