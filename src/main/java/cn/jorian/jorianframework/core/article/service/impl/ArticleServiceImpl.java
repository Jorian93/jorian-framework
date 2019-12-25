package cn.jorian.jorianframework.core.article.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.core.article.dto.ArticleAddDTO;
import cn.jorian.jorianframework.core.article.dto.ArticleFindDTO;
import cn.jorian.jorianframework.core.article.entity.Article;
import cn.jorian.jorianframework.core.article.mapper.ArticleMapper;
import cn.jorian.jorianframework.core.article.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jorian
 * @since 2019-05-13
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void add(ArticleAddDTO articleAddDTO) {
        Article findArticle  = new Article();
        BeanUtils.copyProperties(articleAddDTO,findArticle);
        //更新文章平台表

        findArticle.setCreateTime(LocalDateTime.now());
        findArticle.setUpdateTime(LocalDateTime.now());
        findArticle.setReadCount(0);
        //status = 1,发布
       if(findArticle.getStatus()==1){
            System.out.println("发布文章ing。。。");
        }

        //保存到数据库
        this.save(findArticle);
    }
    @Override
    public void delete(String id) {
        if(id==null){
            throw new ServiceException("要删除的id不能为空");
        }
        Article findArticle = this.getById(id);
        if(findArticle == null) {
            throw new ServiceException("要删除的资源不存在");
        }
        this.removeById(id);
        redisTemplate.delete(id);
    }

    @Override
    public void update(Article article) {

        if(article==null){
            throw new ServiceException("不能保存空的资源");
        }
        Article findArticle = this.getById(article.getId());
        if(findArticle ==null) {
            throw new ServiceException("更新的资源不存在");
        }
        BeanUtils.copyProperties(article,findArticle);
        this.updateById(findArticle);
        redisTemplate.delete(article.getId());

    }

    @Override
    public IPage<Article> getList(ArticleFindDTO articleFindDTO) {
        Article article =new Article();
        BeanUtils.copyProperties(articleFindDTO,article);
        QueryWrapper<Article> queryWrapper =new QueryWrapper<>();
        if(articleFindDTO.getTitle()!=null){
            queryWrapper.eq("status",articleFindDTO.getStatus()) ;
        }
        if(articleFindDTO.getTitle()!=null){
            queryWrapper.eq("title",articleFindDTO.getTitle()) ;
        }
        if(!StringUtils.isEmpty(articleFindDTO.getType())){
            queryWrapper.eq("type",articleFindDTO.getType()) ;
        }
        if(articleFindDTO.getTitle()!=null){
            queryWrapper.eq("author",articleFindDTO.getAuthor()) ;
        }
            queryWrapper.orderByDesc("updateTime");
        return this.page(new Page<>(articleFindDTO.getPage(),articleFindDTO.getLimit()),queryWrapper);
    }

    @Override
    public List<Article> getListNew(Integer type) {
        List<Article> articles = this.list(new QueryWrapper<Article>()
                .eq("type",type)
                .eq("status",1) //必须是发布的文章
                .orderByDesc("updateTime")) ;
        List<Article> articles3 = articles.subList(0,3);
        return articles3;
    }

    @Override
    public List<Article> getListFire(Integer type) {
        List<Article> articles = this.list(new QueryWrapper<Article>()
                .eq("type",type)
                .eq("status",1)//必须是发布的文章
                .orderByDesc("readCount")) ;
        List<Article> articles3 = articles.subList(0,3);
        return articles3;
    }

    @Override
    public Article fetchArticleDetails(String id) {
        Article article = (Article) redisTemplate.opsForValue().get(id);
        if(article!=null){
            return article;
        }
        article = this.getById(id);
        if(article==null){
            throw new ServiceException(ResponseCode.FAIL);
        }
        redisTemplate.opsForValue().set(article.getId(),article);
        return article;
    }


}
