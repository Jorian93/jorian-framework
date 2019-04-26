package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.core.system.dto.UserAddDTO;
import cn.jorian.jorianframework.core.system.dto.UserFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import cn.jorian.jorianframework.core.system.entity.SysUserRole;
import cn.jorian.jorianframework.core.system.mapper.UserMapper;
import cn.jorian.jorianframework.core.system.service.RoleService;
import cn.jorian.jorianframework.core.system.service.UserRoleService;
import cn.jorian.jorianframework.core.system.service.UserService;
import cn.jorian.jorianframework.common.utils.EncryptPassword;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;



/**
 * @Auther: jorian
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

    @Override
    public void add(UserAddDTO userAddDTO) {
        SysUser findUser = this.findUserByUsername(userAddDTO.getUsername());
        if(findUser!=null){
          throw new ServiceException("用户已存在");
        }
        findUser = new SysUser();
        BeanUtils.copyProperties(userAddDTO,findUser);
        findUser.setCreateTime(LocalDateTime.now());
        findUser.setUpdateTime(LocalDateTime.now());
        String MD5Password = EncryptPassword.ENCRYPT_MD5(findUser.getUsername(),findUser.getPassword(),2);
        findUser.setPassword(MD5Password);//密码加密后的user
        try{
            this.save(findUser);
        }catch(Exception e){
            throw new ServiceException("用户插入失败",e);
        }
        //插入用户角色表
        try{
            userAddDTO.getRoles().forEach((item)->{
                SysUser newUser = this.getOne(new QueryWrapper<SysUser>().eq("name",userAddDTO.getUsername()));
                userRoleService.save(new SysUserRole()
                        .setUid(newUser.getId())
                        .setRid(item.getId()));
            });
        }catch (Exception e){
            throw new ServiceException("用户角色表修改失败",e);
        }
    }

    @Override
    public void delete(String id) {
        if(id==null){
            throw new ServiceException("请输入要删除的用户id");
        }
        SysUser findUser = this.getById(id);
        this.getById(id);
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
        this.updateById(findUser);
        //先删除再保存角色关系表
        userRoleService.remove(new QueryWrapper<SysUserRole>().eq("uid",findUser.getId()));
        sysUser.getRoles().forEach((item)->{
            userRoleService.save(new SysUserRole()
                    .setUid(sysUser.getId())
                    .setRid(item.getId()));
        });
    }

    @Override
    public SysUser findUserByUsername(String username) {
        return null;
    }

    @Override
    public Object getList(UserFindDTO userFindDTO) {
        SysUser sysResource =new SysUser();
        BeanUtils.copyProperties(userFindDTO,sysResource);
        QueryWrapper<SysUser> queryWrapper =new QueryWrapper<>();
        if(StringUtils.isEmpty(userFindDTO.getUsername())){
            queryWrapper.eq("username",userFindDTO.getUsername());
        }
        if(StringUtils.isEmpty(userFindDTO.getStatus())){
            queryWrapper.eq("status",userFindDTO.getStatus()) ;
        }
        if(StringUtils.isEmpty(userFindDTO.getId())){
            queryWrapper.eq("id",userFindDTO.getId()) ;
        }
        return this.page(new Page<>(userFindDTO.getPage(),userFindDTO.getLimit()),null);
    }

    @Override
    public Object getTree() {
        //TODO !!
        return null;
    }

}
