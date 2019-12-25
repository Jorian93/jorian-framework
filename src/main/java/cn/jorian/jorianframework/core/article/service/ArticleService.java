package cn.jorian.jorianframework.core.article.service;

import cn.jorian.jorianframework.core.article.dto.ArticleAddDTO;
import cn.jorian.jorianframework.core.article.dto.ArticleFindDTO;
import cn.jorian.jorianframework.core.article.entity.Article;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jorian
 * @since 2019-05-13
 */
public interface ArticleService extends IService<Article> {

    void add(ArticleAddDTO addDTO);

    void delete(String id);

    void update(Article article);

    IPage<Article> getList(ArticleFindDTO articleFindDTO);

    List<Article> getListNew(Integer type);

    List<Article> getListFire(Integer type);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    Article fetchArticleDetails(String id);
}
