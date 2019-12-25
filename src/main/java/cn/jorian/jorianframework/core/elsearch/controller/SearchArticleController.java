package cn.jorian.jorianframework.core.elsearch.controller;

import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
import cn.jorian.jorianframework.core.elsearch.service.SearchArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jorian
 * @date: 2019/11/20 15:02
 * @description: this is  description for the class
 */
@RestController
@RequestMapping("/elsearch")
@Api(tags = "全文检索")
public class SearchArticleController {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    SearchArticleService searchArticleService;

    /**
     * 全文检索（根据整个实体的所有属性，可能结果为0个）
     * @param q
     * @return
     */
    @GetMapping("/article/select/{q}")
    @ApiOperation(value = "搜索one")
    public ResponseResult searchOne(@PathVariable @ApiParam("查询字段") String q) {
        return new ResponseResult(ResponseCode.SUCCESS,searchArticleService.searchOne(q));
    }

    /**
     * 全文检索 查询所有
     * @throws Exception
     */
    @GetMapping("/articles/all")
    @ApiOperation(value = "搜索all")
    public ResponseResult searchAll(){
       return new ResponseResult(ResponseCode.SUCCESS,searchArticleService.searchAll());
    }


}
