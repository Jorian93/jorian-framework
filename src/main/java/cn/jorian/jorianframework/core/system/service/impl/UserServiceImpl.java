package cn.jorian.jorianframework.core.system.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.core.system.dto.UserAddDTO;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import cn.jorian.jorianframework.core.system.entity.SysUserRole;
import cn.jorian.jorianframework.core.system.mapper.UserMapper;
import cn.jorian.jorianframework.core.system.service.RoleService;
import cn.jorian.jorianframework.core.system.service.UserRoleService;
import cn.jorian.jorianframework.core.system.service.UserService;
import cn.jorian.jorianframework.common.utils.EncryptPassword;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        this.save(findUser);
        this.updateUserRoles(findUser);

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(SysUser sysUser) {

    }

    @Override
    public SysUser findUserByUsername(String username) {
        return null;
    }

    public void updateUserRoles(SysUser sysUser){

        try {
             userRoleService.remove(new QueryWrapper<SysUserRole>().eq("uid",sysUser.getId()));
            if(sysUser.getRoles()!=null && sysUser.getRoles().size()>0){
                sysUser.getRoles().forEach(v-> userRoleService.save(SysUserRole.builder()
                        .uid(sysUser.getId())
                        .rid(v.getId()).build()));

            }
        }catch (Exception e){
            throw new ServiceException("用户角色映射修改失败",e);
        }
    }
}
