package cn.jorian.jorianframework.core.system.controller;


import cn.jorian.jorianframework.common.annotation.Log;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.SystemResponse;
import cn.jorian.jorianframework.core.system.dto.LogFindDTO;
import cn.jorian.jorianframework.core.system.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jorian
 * @since 2019-04-24
 */
@RestController
@RequestMapping("/system")
@Api(tags = "日志管理")
public class LogController {
    @Autowired
    LogService logService;

    @ApiOperation("日志列表查询")
    @RequestMapping(value = "/log/list", method = RequestMethod.GET)
    @Log("查询日志")
    public SystemResponse roleList(LogFindDTO roleFindDTO) {
        return new SystemResponse(ResponseCode.SUCCESS, logService.getList(roleFindDTO));
    }

    @ApiOperation("删除日志")
    @RequestMapping(value = "log/delete/{id}", method = RequestMethod.DELETE)
    @Log("删除日志")
    @RequiresPermissions("[log:list:jorian]")
    public SystemResponse roleList(@PathVariable("id") @ApiParam(value = "日志id")String id) {
        return new SystemResponse(ResponseCode.SUCCESS, logService.removeById(id));
    }

}

