package cn.jorian.jorianframework.core.article.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.core.article.dto.CommontAddDTO;
import cn.jorian.jorianframework.core.article.dto.CommontFindDTO;
import cn.jorian.jorianframework.core.article.dto.CommontUpdateDTO;
import cn.jorian.jorianframework.core.article.entity.Commont;
import cn.jorian.jorianframework.core.article.entity.Support;
import cn.jorian.jorianframework.core.article.mapper.CommontMapper;
import cn.jorian.jorianframework.core.article.service.CommontService;
import cn.jorian.jorianframework.core.article.service.SupportService;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import cn.jorian.jorianframework.core.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * commont 服务实现类
 * </p>
 *
 * @author jorian
 * @since 2019-09-19
 */
@Service
public class CommontServiceImpl extends ServiceImpl<CommontMapper, Commont> implements CommontService {

    @Autowired
    UserService userService;

    @Autowired
    SupportService supportService;
    @Override
    public void add(CommontAddDTO addDTO) {
        Commont newCommont  = new Commont();
        BeanUtils.copyProperties(addDTO,newCommont);
        //评论者id,临时
        newCommont.setComUserId("1011111111");
        newCommont.setCreateTime(LocalDateTime.now());
        newCommont.setUpdateTime(LocalDateTime.now());
        newCommont.setSupport(0L);
        //保存到数据库
        this.save(newCommont);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(CommontUpdateDTO updateDTO) {

        Commont findCommont = this.getById(updateDTO.getId());
        if(findCommont==null){
            throw new ServiceException("要更新的评价为空",-1);
        }
        BeanUtils.copyProperties(updateDTO,findCommont);
        findCommont.setUpdateTime(LocalDateTime.now());
        this.updateById(findCommont);

    }

    @Override
    public IPage<Commont> getList(CommontFindDTO findDTO) {
        Commont commont =new Commont();
        BeanUtils.copyProperties(findDTO,commont);
        QueryWrapper<Commont> queryWrapper =new QueryWrapper<>();
        //此处可以拼接查询条件
        //回答的id
        if(!StringUtils.isEmpty(findDTO.getAid())){
            queryWrapper.eq("aid",findDTO.getAid());
        }
        queryWrapper.orderByDesc("createTime");

        IPage page = this.page(new Page<>(findDTO.getPage(),findDTO.getLimit()),queryWrapper);
        //拼接用户信息
        List<Commont> commontList = page.getRecords();
        commontList.forEach(item->{
            //对能否点赞进行处理
            String currUserId = findDTO.getCurrUserId();
            //查找是否有此用户对此条信息点赞的记录
            Support support = supportService.getOne(new QueryWrapper<Support>()
                    .eq("pid",item.getId())
                    .eq("supportUserId",findDTO.getCurrUserId())
            );
            if(support!=null){
                item.setCanLiko(false);
            }else{
                item.setCanLiko(true);
            }
            //拼接用户信息
            SysUser sysUser = userService.getById(item.getComUserId());
            item.setSysUser(sysUser);
        });
        return page;
    }
}
