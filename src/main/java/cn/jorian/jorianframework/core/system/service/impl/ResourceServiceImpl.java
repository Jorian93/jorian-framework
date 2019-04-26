package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.core.system.dto.ResourceAddDTO;
import cn.jorian.jorianframework.core.system.dto.ResourceFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysResource;
import cn.jorian.jorianframework.core.system.mapper.ResourceMapper;
import cn.jorian.jorianframework.core.system.service.ResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: jorian
 * @Date: 2019/4/18 16:11
 * @Description:
 */
@Service
@Transactional
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, SysResource> implements ResourceService {
    @Override
    public void add(ResourceAddDTO resourceAddDTO) {
        SysResource findReource = this.getOne(new QueryWrapper<SysResource>().eq("url",resourceAddDTO.getUrl()));
        if(findReource!=null){
            throw new ServiceException("该资源路径已存在");
        }
        findReource = new SysResource();
        BeanUtils.copyProperties(resourceAddDTO,findReource);
        findReource.setCreateTime(LocalDateTime.now());
        findReource.setUpdateTime(LocalDateTime.now());
        this.save(findReource);
    }
    @Override
    public void delete(String id) {
        if(id==null){
           throw new ServiceException("请输入要删除的id");
        }
        SysResource findResource = this.getById(id);
        this.getById(id);
        if(findResource == null) {
            throw new ServiceException("要删除的资源不存在");
        }
        this.removeById(id);
    }

    @Override
    public void update(SysResource sysResource) {

      if(sysResource==null){
          throw new ServiceException("不能保存空的资源");
      }
      SysResource findResource = this.getById(sysResource.getId());
      if(findResource ==null) {
          throw new ServiceException("更新的资源不存在");
      }
        BeanUtils.copyProperties(sysResource,findResource);
        this.updateById(findResource);
        //shiroService.reloadPerms();//shiro重新加载权限表
    }

    @Override
    public IPage<SysResource> getList(ResourceFindDTO resourceFindDTO) {
        SysResource sysResource =new SysResource();
        BeanUtils.copyProperties(resourceFindDTO,sysResource);
        QueryWrapper<SysResource> queryWrapper =new QueryWrapper<>();
       if(resourceFindDTO.getName()!=null){
            queryWrapper.eq("name",resourceFindDTO.getName()) ;
        }
        return this.page(new Page<>(resourceFindDTO.getPage(),resourceFindDTO.getLimit()),queryWrapper);
    }

    @Override
    public List<SysResource> getTree() {
        QueryWrapper<SysResource> wrapper = new QueryWrapper<>();
        wrapper.eq("pid",0)
                .or()
                .isNull("pid")
                .orderByAsc("sort");
        List<SysResource> resourcesTree = this.list(wrapper);
        if(resourcesTree!=null && resourcesTree.size()>0){
            resourcesTree.forEach(this::findAllChild);
        }
        return resourcesTree;
    }


    public void findAllChild(SysResource resource){
        QueryWrapper<SysResource> wrapper = new QueryWrapper<>();
        wrapper.eq("pid",resource.getId()).orderByAsc("sort");
        List<SysResource> resources = this.list(wrapper);
        resource.setChildren(resources);
        if(resources!=null && resources.size()>0){
            resources.forEach(this::findAllChild);
        }
    }
}
