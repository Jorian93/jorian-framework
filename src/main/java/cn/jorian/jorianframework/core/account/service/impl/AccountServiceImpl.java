package cn.jorian.jorianframework.core.account.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.JTokenUtil;
import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.core.account.dto.LoginDTO;
import cn.jorian.jorianframework.core.account.service.AccountService;

import cn.jorian.jorianframework.core.system.entity.*;
import cn.jorian.jorianframework.core.system.mapper.UserMapper;
import cn.jorian.jorianframework.core.system.service.ResourceService;
import cn.jorian.jorianframework.core.system.service.RoleResourceService;
import cn.jorian.jorianframework.core.system.service.RoleService;
import cn.jorian.jorianframework.core.system.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: jorian
 * @Date: 2019/4/18 14:08
 * @Description:
 */
@Service
public class AccountServiceImpl  extends ServiceImpl<UserMapper,SysUser> implements AccountService {

    @Autowired
    UserRoleService  userRoleService;

    @Autowired
    RoleResourceService roleResourceService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    RoleService roleService;


    @Override
    public void login(LoginDTO loginDTO) {
        if(StringUtils.isEmpty(loginDTO)){

        }
        if( "".equals(loginDTO.getUsername()) || "".equals(loginDTO.getPassword()) ){
            throw new ServiceException(ResponseCode.SIGN_IN_USERNAME_PASSWORD_EMPTY.msg);
        }
        JToken token = new JToken(null,loginDTO.getUsername(),loginDTO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if(!subject.isAuthenticated()){
                throw new ServiceException(ResponseCode.TOKEN_AUTHENTICATION_FAIL.msg);
            }
        }catch (DisabledAccountException e){
            throw new ServiceException(ResponseCode.USER_ISLOCKED.msg,e);
        }catch (Exception e){
            throw new ServiceException(ResponseCode.SIGN_IN_FAIL.msg,e);
        }
    }

    @Override
    public SysUser getCurrentUser() {
        String token = ((JToken)SecurityUtils.getSubject().getPrincipal()).getToken();
        if(token==null){
            throw new ServiceException("token不存在或者已过期");
        }
        String username = JTokenUtil.get(token,"username");
        SysUser findUser = this.getOne(new QueryWrapper<SysUser>().eq("username",username));
        //需给用户加角色表
        List<SysRole> roles = new ArrayList<>();
        List<SysUserRole> sysUserRoleLsit = userRoleService.list(new QueryWrapper<SysUserRole>().eq("uid",findUser.getId()));
        sysUserRoleLsit.forEach(item ->{
            SysRole sysRole = roleService.getById(item.getRid());
            roles.add(sysRole);
        });
        findUser.setRoles(roles);
        return findUser;
    }

    @Override
    public List<SysResource> getCurrentUserResource() {

        String token = ((JToken)SecurityUtils.getSubject().getPrincipal()).getToken();
        if(token==null){
            throw new ServiceException("token不存在或者已过期");
        }
        String username = JTokenUtil.get(token,"username");
        SysUser findUser = this.getOne(new QueryWrapper<SysUser>().eq("username",username));
        List<SysResource> resources = new ArrayList<>();
        //根据用户id查找用户角色列表
        List<SysUserRole> userRoles = userRoleService.list(new QueryWrapper<SysUserRole>().eq("uid",findUser.getId()));
        userRoles.forEach(userRole -> {
            //根据用户角色表的角色id列表
            List<SysRoleResource> roleResources = roleResourceService.list(new QueryWrapper<SysRoleResource>().eq("roleId",userRole.getRid()));
            roleResources.forEach(roleResource ->{
                List<SysResource> resources1 = resourceService.list(new QueryWrapper<SysResource>().eq("id",roleResource.getResourceId()));
                resources1.forEach(resource ->{
                    if(!resources.contains(resource)){
                        resources.add(resource);
                    }
                });
            });
        });

        //组织成树结构
         List<SysResource> treeList = new ArrayList<>();
         resources.forEach(item ->{
         //添加一级
             if(item.getPid()=="0"){
                treeList.add(item);
             }
        });

         //给爸爸找儿子
        addChildrenToParrent(treeList,resources);

        /*treeList.forEach(item ->{
            List<SysResource> children = new ArrayList<>();
             resources.forEach(r ->{
                if(item.getId()==r.getPid()){
                    children.add(r);

                }
            });
            item.setChildren(children);
        });*/

        return  treeList;
    }

    public void addChildrenToParrent(List<SysResource> operationList,List<SysResource> allResource){

        //得到二级
        operationList.forEach(item ->{
            List<SysResource> children = new ArrayList<>();
            allResource.forEach(r ->{
                if(item.getId()==r.getPid()){
                    children.add(r);
                }
            });
            item.setChildren(children);
        });


    }



}
