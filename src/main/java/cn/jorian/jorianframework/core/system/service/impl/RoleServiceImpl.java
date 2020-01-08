package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.core.system.dto.RoleAddDTO;
import cn.jorian.jorianframework.core.system.dto.RoleFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysRole;
import cn.jorian.jorianframework.core.system.entity.SysRoleResource;
import cn.jorian.jorianframework.core.system.entity.SysUserRole;
import cn.jorian.jorianframework.core.system.mapper.RoleMapper;
import cn.jorian.jorianframework.core.system.service.RoleResourceService;
import cn.jorian.jorianframework.core.system.service.RoleService;
import cn.jorian.jorianframework.core.system.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @Author: jorian
 * @Date: 2019/4/17 11:09
 * @Description:
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    @Autowired
    RoleResourceService roleResourceService;
    @Autowired
    UserRoleService userRoleService;
    @Override
    public void add(RoleAddDTO roleAddDTO) {
        SysRole findRole = this.getOne(new QueryWrapper<SysRole>().eq("name",roleAddDTO.getName()));
        if(findRole!=null){
            throw new ServiceException("该角色已存在");
        }
        findRole = new SysRole();
        BeanUtils.copyProperties(roleAddDTO,findRole);
        findRole.setCreateTime(LocalDateTime.now());
        findRole.setUpdateTime(LocalDateTime.now());
        this.save(findRole);
        //保存角色资源表
        roleAddDTO.getResources().forEach((item)->{
            SysRole newRole = this.getOne(new QueryWrapper<SysRole>().eq("name",roleAddDTO.getName()));
            roleResourceService.save(new SysRoleResource()
                    .setRoleId(newRole.getId())
                    .setResourceId(item.getId()));
        });

    }

    @Override
    public void delete(String id) {
        if(id==null){
            throw new ServiceException("请输入要删除的id");
        }
        SysRole findRole = this.getById(id);
        this.getById(id);
        if(findRole == null) {
            throw new ServiceException("要删除的资源不存在");
        }
        this.removeById(id);
        //并删除角色资源关系表
        roleResourceService.remove(new QueryWrapper<SysRoleResource>().eq("roleId",id));
        //并删除用户角色关系表
        userRoleService.remove(new QueryWrapper<SysUserRole>().eq("rid",id));
    }
    @Override
    public void update(SysRole sysRole) {
        if(sysRole==null){
            throw new ServiceException("不能保存空的角色");
        }
        SysRole findRole = this.getById(sysRole.getId());
        if(findRole ==null) {
            throw new ServiceException("更新的角色不存在");
        }
        BeanUtils.copyProperties(sysRole,findRole);
        this.updateById(findRole);
        //先删除再保存角色关系表
        roleResourceService.remove(new QueryWrapper<SysRoleResource>().eq("roleId",findRole.getId()));
        sysRole.getResources().forEach((item)->{
            roleResourceService.save(new SysRoleResource()
                    .setRoleId(sysRole.getId())
                    .setResourceId(item.getId()));
        });

    }
    @Override
    public IPage<SysRole> getList(RoleFindDTO roleFindDTO) {
        SysRole sysRole =new SysRole();
        BeanUtils.copyProperties(roleFindDTO,sysRole);
        QueryWrapper<SysRole> queryWrapper =new QueryWrapper<>();
        if(!StringUtils.isEmpty(roleFindDTO.getName())){
            queryWrapper.eq("name",roleFindDTO.getName()) ;
        }
        return this.page(new Page<>(roleFindDTO.getPage(),roleFindDTO.getLimit()),queryWrapper);

    }

    @Override
    public Object getTree() {
        //TODO 留下来！
        return null;
    }
}
