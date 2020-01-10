package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.utils.JTool_EncryptPassword;
import cn.jorian.jorianframework.core.system.dto.UserAddDTO;
import cn.jorian.jorianframework.core.system.dto.UserFindDTO;
import cn.jorian.jorianframework.core.system.entity.*;
import cn.jorian.jorianframework.core.system.mapper.UserMapper;
import cn.jorian.jorianframework.core.system.service.*;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @Author: jorian
 * @Date: 2019/4/17 10:39
 * @Description:
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleResourceService roleResourceService;
    @Autowired
    ResourceService resourceService;

    @Override
    public void add(UserAddDTO userAddDTO) {

        SysUser findUser = this.getOne(new QueryWrapper<SysUser>().eq("username",userAddDTO.getUsername()));
        if(findUser!=null){
          throw new ServiceException("用户已存在");
        }
        findUser = new SysUser();
        BeanUtils.copyProperties(userAddDTO,findUser);
        findUser.setCreateTime(LocalDateTime.now());
        findUser.setUpdateTime(LocalDateTime.now());
        String MD5Password = JTool_EncryptPassword.ENCRYPT_MD5(findUser.getUsername(),findUser.getPassword());
        findUser.setPassword(MD5Password);//密码加密后的user
        try{
            this.save(findUser);
            //插入用户角色表
            try{
                userAddDTO.getRoles().forEach((item)->{
                    SysUser newUser = this.getOne(new QueryWrapper<SysUser>().eq("username",userAddDTO.getUsername()));
                    userRoleService.save(new SysUserRole()
                            .setUid(newUser.getId())
                            .setRid(item.getId()));
                });
            }catch (Exception e){
                throw new ServiceException("用户角色表修改失败",e);
            }
        }catch(Exception e){
            throw new ServiceException("用户保存失败",e);
        }
    }

    @Override
    public void delete(String id) {
        if(id==null){
            throw new ServiceException("请输入要删除的用户id");
        }
        SysUser findUser = this.getById(id);
        //this.getById(id);
        if(findUser == null) {
            throw new ServiceException("要删除的用户不存在");
        }
        this.removeById(id);
        //并删除用户角色关系表
        userRoleService.remove(new QueryWrapper<SysUserRole>().eq("Uid",id));
    }

    @Override
    public void update(SysUser sysUser) {
        if(sysUser==null){
            throw new ServiceException("不能保存空的资源");
        }
        SysUser findUser = this.getById(sysUser.getId());
        if(findUser ==null) {
            throw new ServiceException("更新的资源不存在");
        }
        BeanUtils.copyProperties(sysUser,findUser);
        findUser.setUpdateTime(LocalDateTime.now());
        this.updateById(findUser);
        //先删除再保存角色关系表
        userRoleService.remove(new QueryWrapper<SysUserRole>().eq("uid",sysUser.getId()));
        sysUser.getRoles().forEach((item)->{
            userRoleService.save(new SysUserRole()
                    .setUid(sysUser.getId())
                    .setRid(item.getId()));
        });
    }

    @Override
    public SysUser findUserByUsername(String username) {
        SysUser findUser = this.getOne(new QueryWrapper<SysUser>().eq("username",username));
        //根据用户id查找用户角色列表
        List <SysRole> roles = new ArrayList<>();
        List<SysUserRole> userRoles = userRoleService.list(new QueryWrapper<SysUserRole>().eq("uid",findUser.getId()));
        List <SysResource> resources = new ArrayList<>();
        userRoles.forEach(userRole -> {
            SysRole sysRole = roleService.getById(userRole.getRid());
            //根据用户角色表的角色id列表
            List<SysRoleResource> roleResources = roleResourceService.list(new QueryWrapper<SysRoleResource>().eq("roleId",userRole.getRid()));
            roleResources.forEach(roleResource ->{
                //根据角色得到多个资源表
                List <SysResource> resources1 = resourceService.list(new QueryWrapper<SysResource>().eq("id",roleResource.getResourceId()));
                sysRole.setResources(resources1);
                roles.add(sysRole);
            });
            findUser.setRoles(roles);
        });
        //组装资源
        return findUser;
    }

    @Override
    public IPage<SysUser> getList(UserFindDTO userFindDTO) {
        SysUser sysResource =new SysUser();
        BeanUtils.copyProperties(userFindDTO,sysResource);
        QueryWrapper<SysUser> queryWrapper =new QueryWrapper<>();
        if(!StringUtils.isEmpty(userFindDTO.getUsername())){
            queryWrapper.eq("username",userFindDTO.getUsername());
        }
        if(!StringUtils.isEmpty(userFindDTO.getStatus())){
            queryWrapper.eq("status",userFindDTO.getStatus());
        }
        if(!StringUtils.isEmpty(userFindDTO.getNickname())){
            queryWrapper.eq("nickname",userFindDTO.getNickname());
        }
        if(!StringUtils.isEmpty(userFindDTO.getNickname())){
            queryWrapper.eq("company",userFindDTO.getCompany());
        }

        IPage<SysUser> pagedata = this.page(new Page<>(userFindDTO.getPage(),userFindDTO.getLimit()),queryWrapper);
        //填充角色
        pagedata.getRecords().forEach(item->{
            List<SysRole> sysRoleList = new ArrayList<>();
            List<SysUserRole> userRoleList = userRoleService.list(new QueryWrapper<SysUserRole>().eq("uid",item.getId()));
            userRoleList.forEach(userRole->{
            SysRole sysRole = roleService.getById(userRole.getRid());
                sysRoleList.add(sysRole);
            });
                item.setRoles(sysRoleList);
        });
        return pagedata;


    }

    @Override
    public List<SysUser> getTree() {
        //TODO !!
        return null;
    }

    @Override
    public Set<String> getUserPermissions(String username) {

        SysUser findUser = this.getOne(new QueryWrapper<SysUser>().eq("username",username));
        //根据用户id查找用户角色列表
        List<SysUserRole> userRoles = userRoleService.list(new QueryWrapper<SysUserRole>().eq("uid",findUser.getId()));
        //资源总表
        List<SysResource> resources = new ArrayList<>();
        userRoles.forEach(userRole -> {
            //根据用户角色表的角色id列表
            List<SysRoleResource> roleResources = roleResourceService.list(new QueryWrapper<SysRoleResource>().eq("roleId",userRole.getRid()));
            roleResources.forEach(roleResource ->{
                //根据角色得到多个资源表
                List <SysResource> resources1 = resourceService.list(new QueryWrapper<SysResource>().eq("id",roleResource.getResourceId()));
                resources.addAll(resources1);
            });
        });
        //总表去重
        Set<String> pSet=new HashSet<String>();
        resources.forEach(resource->{
            pSet.add(resource.getPermission());
        });
        return pSet;
    }

    @Override
    public List<SysUser> serarch(String name) {
        return this.list(new QueryWrapper<SysUser>().likeRight("nickname",name));
    }

}
