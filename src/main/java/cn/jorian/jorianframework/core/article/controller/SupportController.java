package cn.jorian.jorianframework.core.article.controller;


import cn.jorian.jorianframework.common.annotation.JLog;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
import cn.jorian.jorianframework.core.article.dto.SupportAddDTO;
import cn.jorian.jorianframework.core.article.service.SupportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jorian
 * @since 2019-10-11
 */
@RestController
@RequestMapping("/ask/support")
@Api(tags = "点赞管理")
public class SupportController {
    @Autowired
    SupportService supportService;
    @ApiOperation(value = "新增点赞")
    @RequestMapping(value = "/support", method = RequestMethod.POST)
    @JLog("新增点赞")
    public ResponseResult addQuestion(@RequestBody @Validated @ApiParam(value = "点赞数据") SupportAddDTO addDTO) {
        supportService.add(addDTO);
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @ApiOperation("删除点赞")
    @RequestMapping(value = "/support/{id}", method = RequestMethod.DELETE)
    @JLog("删除点赞")
    public ResponseResult deleteQuestion(@PathVariable("id") @ApiParam(value = "点赞id") String id) {
        supportService.delete(id);
        return new ResponseResult(ResponseCode.SUCCESS);
    }
}

