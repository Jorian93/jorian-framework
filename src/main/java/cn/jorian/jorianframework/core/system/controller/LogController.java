package cn.jorian.jorianframework.core.system.controller;


import cn.jorian.jorianframework.common.annotation.JLog;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
import cn.jorian.jorianframework.core.system.dto.LogDeleteDTO;
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

import java.util.List;

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
    @JLog("查询日志")
    public ResponseResult logList(@ApiParam(value = "日志查询条件") LogFindDTO logFindDTO) {
        return new ResponseResult(ResponseCode.SUCCESS, logService.getList(logFindDTO));
    }

    @ApiOperation("删除日志")
    @RequestMapping(value = "log/delete/{id}", method = RequestMethod.DELETE)
    @JLog("删除日志")
    @RequiresPermissions("[log:delete:jorian]")
    public ResponseResult logDelete(@PathVariable("id") @ApiParam(value = "要删除的日志id")String id) {
        return new ResponseResult(ResponseCode.SUCCESS, logService.removeById(id));
    }

    @ApiOperation("按照id批量删除日志")
    @RequestMapping(value = "log/delete/ids", method = RequestMethod.DELETE)
    @JLog("删除日志")
    @RequiresPermissions("[log:delete:jorian]")
    public ResponseResult logDeleteByIds(@ApiParam(value = "日志id集合")List<String > ids) {
        logService.deleteByIds(ids);
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @ApiOperation("按起止日期删除日志")
    @RequestMapping(value = "log/delete/date/", method = RequestMethod.DELETE)
    @JLog("删除日志")
    @RequiresPermissions("[log:delete:jorian]")
    public ResponseResult logDeleteByDate(@ApiParam(value = "日志删除起止日期") LogDeleteDTO logDeleteDTO) {
        logService.deleteByDate(logDeleteDTO);
        return new ResponseResult(ResponseCode.SUCCESS);
    }


}

