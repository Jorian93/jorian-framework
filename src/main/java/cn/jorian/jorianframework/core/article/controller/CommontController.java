package cn.jorian.jorianframework.core.article.controller;


import cn.jorian.jorianframework.common.annotation.JLog;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
import cn.jorian.jorianframework.core.article.dto.CommontAddDTO;
import cn.jorian.jorianframework.core.article.dto.CommontFindDTO;
import cn.jorian.jorianframework.core.article.dto.CommontUpdateDTO;
import cn.jorian.jorianframework.core.article.service.CommontService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * commont 前端控制器
 * </p>
 *
 * @author jorian
 * @since 2019-09-19
 */
@RestController
@RequestMapping("/ask")
@Api(tags = "评论管理")
public class CommontController {
    @Autowired
    private CommontService commontService;

    @ApiOperation(value = "新增评论")
    @RequestMapping(value = "/commont", method = RequestMethod.POST)
    @JLog("新增评论")
    public ResponseResult addQuestion(@RequestBody @Validated @ApiParam(value = "评论数据") CommontAddDTO addDTO) {
        commontService.add(addDTO);
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @ApiOperation("删除评论")
    @RequestMapping(value = "/commont/{id}", method = RequestMethod.DELETE)
    @JLog("删除评论")
    public ResponseResult deleteQuestion(@PathVariable("id") @ApiParam(value = "评论id") String id) {
        commontService.delete(id);
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @ApiOperation("更新评论")
    @RequestMapping(value = "/commonts", method = RequestMethod.POST)
    @JLog("更新评论")
    public ResponseResult updateQuestion(@RequestBody @Validated @ApiParam(value = "评论更新数据") CommontUpdateDTO updateDTO) {
        commontService.update(updateDTO);
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @ApiOperation(value = "评论详情")
    @RequestMapping(value = "/commont/{id}", method = RequestMethod.GET)
    @JLog("评论详情")
    public ResponseResult fetchQuestion(@PathVariable("id") @ApiParam(value = "评论id") String id) {
        return new ResponseResult(ResponseCode.SUCCESS,commontService.getById(id));
    }

    @ApiOperation("评论列表查询")
    @RequestMapping(value = "/commonts", method = RequestMethod.GET)
    @JLog("查询评论列表")
    public ResponseResult listQuestion(CommontFindDTO findDTO) {
        return new ResponseResult(ResponseCode.SUCCESS, commontService.getList(findDTO));
    }
}

