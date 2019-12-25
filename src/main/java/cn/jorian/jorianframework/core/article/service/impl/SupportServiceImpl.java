package cn.jorian.jorianframework.core.article.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.core.article.dto.SupportAddDTO;
import cn.jorian.jorianframework.core.article.entity.Support;
import cn.jorian.jorianframework.core.article.mapper.SupportMapper;
import cn.jorian.jorianframework.core.article.service.SupportService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jorian
 * @since 2019-10-11
 */
@Service
public class SupportServiceImpl extends ServiceImpl<SupportMapper, Support> implements SupportService {

    @Override
    public void add(SupportAddDTO addDTO) {
        //是否已经收藏

        Support findSupport = this.getOne(new QueryWrapper<Support>()
                .eq("pid",addDTO.getPid())
                .eq("supportUserId",addDTO.getSupportUserId())
        );
        if(findSupport!=null){
            throw new ServiceException("已存在数据",-1);
        }
        //收藏
        findSupport = new Support();
        BeanUtils.copyProperties(addDTO,findSupport);
        findSupport.setCreateTime(LocalDateTime.now());
        this.save(findSupport);
    }

    @Override
    public void delete(String id) {
        if(id==null){
            throw new ServiceException("要删除的id不能为空",-1);
        }
        Support findSupport = this.getById(id);
        if(findSupport == null) {
            throw new ServiceException("要删除的资源不存在",-1);
        }
        this.removeById(id);
    }
}
