package cn.jorian.jorianframework.core.system.controller;

import cn.jorian.jorianframework.common.annotation.JLog;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
import cn.jorian.jorianframework.core.system.dto.ResourceAddDTO;
import cn.jorian.jorianframework.core.system.dto.ResourceFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysResource;
import cn.jorian.jorianframework.core.system.service.ResourceService;
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
@Api(tags = {"资源管理"})
@RequestMapping("system")
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @ApiOperation("新增资源")
    @RequestMapping(value="/resource/add",method = RequestMethod.POST)
    @JLog("新增资源")
    public ResponseResult resourceAdd(@RequestBody @Validated @ApiParam(value = "资源基础信息") ResourceAddDTO resourceAddDTO){
        resourceService.add(resourceAddDTO);
        return new ResponseResult(ResponseCode.SUCCESS);
    }
    @ApiOperation("删除资源")
    @RequestMapping(value="/resource/delete/{id}",method = RequestMethod.DELETE)
    @JLog("删除资源")
    public ResponseResult resourceDelete(@PathVariable("id") @ApiParam(value = "资源id")String id){
        resourceService.delete(id);
        return new ResponseResult(ResponseCode.SUCCESS);
    }
    @ApiOperation("更新资源")
    @RequestMapping(value="/resource/update",method = RequestMethod.PUT)
    @JLog("更新资源")
    public ResponseResult resourceUpdate(@RequestBody @Validated @ApiParam(value = "资源更新信息")SysResource sysResource){
        resourceService.update(sysResource);
        return new ResponseResult(ResponseCode.SUCCESS);
    }
    @ApiOperation("资源列表查询")
    @RequestMapping(value="/resource/list",method = RequestMethod.GET)
    @JLog("查询资源列表")
    public ResponseResult resourceList(@RequestBody @Validated @ApiParam(value = "资源查询条件") ResourceFindDTO resourceFindDTO){
        return new ResponseResult(ResponseCode.SUCCESS, resourceService.getList(resourceFindDTO));
    }
    @ApiOperation("资源树查询")
    @RequestMapping(value="/resource/tree",method = RequestMethod.GET)
    @JLog("查询资源树")
    public ResponseResult resourceTree(){
        return new ResponseResult(ResponseCode.SUCCESS,resourceService.getTree());
    }

    @ApiOperation("按角色查询资源树")
    @RequestMapping(value="/resource/{rid}",method = RequestMethod.GET)
    @JLog("查看角色资源")
    public ResponseResult resourceUserTree(@PathVariable("rid") String rid){
        return new ResponseResult(ResponseCode.SUCCESS,resourceService.getUserTree(rid));
    }


}
