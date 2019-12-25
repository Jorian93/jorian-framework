package cn.jorian.jorianframework.core.article.service;

import cn.jorian.jorianframework.core.article.dto.CommontAddDTO;
import cn.jorian.jorianframework.core.article.dto.CommontFindDTO;
import cn.jorian.jorianframework.core.article.dto.CommontUpdateDTO;
import cn.jorian.jorianframework.core.article.entity.Commont;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * commont 服务类
 * </p>
 *
 * @author jorian
 * @since 2019-09-19
 */
public interface CommontService extends IService<Commont> {
    /**
     *新增评论
     * @param addDTO
     */
    void add(CommontAddDTO addDTO);

    /**
     * 根据id删除评论
     * @param id
     */
    void delete(String id);

    /**
     * 更新评论
     * @param updateDTO
     */
    void update(CommontUpdateDTO updateDTO);

    /**
     * 根据用户id和评论分类查询列表
     * @param findDTO
     * @return
     */
    IPage<Commont> getList(CommontFindDTO findDTO);
}
