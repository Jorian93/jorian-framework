package cn.jorian.jorianframework.core.article.service;


import cn.jorian.jorianframework.core.article.dto.SupportAddDTO;
import cn.jorian.jorianframework.core.article.entity.Support;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jorian
 * @since 2019-10-11
 */
public interface SupportService extends IService<Support> {
    /**
     *新增历史
     * @param addDTO
     */
    void add(SupportAddDTO addDTO);

    /**
     * 根据id删除历史
     * @param id
     */
    void delete(String id);
}
