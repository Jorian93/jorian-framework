package cn.jorian.jorianframework.core.article.controller;


import cn.jorian.jorianframework.common.annotation.JLog;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
import cn.jorian.jorianframework.core.article.dto.ArticleAddDTO;
import cn.jorian.jorianframework.core.article.dto.ArticleFindDTO;
import cn.jorian.jorianframework.core.article.entity.Article;
import cn.jorian.jorianframework.core.article.service.ArticleService;
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
 * @since 2019-05-13
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章管理")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "新增文章")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @JLog("新增文章")
    public ResponseResult addArticle(@RequestBody @Validated @ApiParam(value = "文章数据") ArticleAddDTO addDTO) {
        articleService.add(addDTO);
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @ApiOperation("删除文章")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @JLog("删除文章")
    public ResponseResult deleteArticle(@PathVariable("id") @ApiParam(value = "文章id") String id) {
        articleService.delete(id);
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @ApiOperation("更新文章")
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    @JLog("更新文章")
    public ResponseResult updateArticle(@RequestBody @Validated @ApiParam(value = "文章更新数据") Article article) {
        articleService.update(article);
        return new ResponseResult(ResponseCode.SUCCESS);
    }
    @ApiOperation(value = "文章详情")
    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    @JLog("查看文章详情")
    public ResponseResult fetchArticle(@PathVariable("id") @ApiParam(value = "文章id") String id) {
        return new ResponseResult(ResponseCode.SUCCESS,articleService.fetchArticleDetails(id));
    }
    @ApiOperation("文章列表查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @JLog("查询文章列表")
    public ResponseResult listArticle(ArticleFindDTO articleFindDTO) {
        return new ResponseResult(ResponseCode.SUCCESS, articleService.getList(articleFindDTO));
    }

    @ApiOperation("最新文章列表查询")
    @RequestMapping(value = "/listNew/{type}", method = RequestMethod.GET)
    @JLog("查询文章列表")
    public ResponseResult listArticleNews(@PathVariable("type") @ApiParam(value = "文章类型") Integer type) {
        return new ResponseResult(ResponseCode.SUCCESS, articleService.getListNew(type));
    }

    @ApiOperation("热门文章列表查询")
    @RequestMapping(value = "/listFire/{type}", method = RequestMethod.GET)
    @JLog("查询文章列表")
    public ResponseResult listArticleFires(@PathVariable("type") @ApiParam(value = "文章类型") Integer type) {
        return new ResponseResult(ResponseCode.SUCCESS, articleService.getListFire(type));
    }


}

