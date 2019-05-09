package cn.jorian.jorianframework.core.account.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.EncryptPassword;
import cn.jorian.jorianframework.common.utils.JTokenUtil;
import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.core.account.dto.LoginDTO;
import cn.jorian.jorianframework.core.account.dto.RestPasswordDTO;
import cn.jorian.jorianframework.core.account.dto.Router;
import cn.jorian.jorianframework.core.account.service.AccountService;

import cn.jorian.jorianframework.core.system.entity.*;
import cn.jorian.jorianframework.core.system.mapper.UserMapper;
import cn.jorian.jorianframework.core.system.service.*;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    UserService userService;

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
    public List<Router> getCurrentUserResource() {
            String token = ((JToken) SecurityUtils.getSubject().getPrincipal()).getToken();

        if(token==null){
            throw new ServiceException("token不存在或者已过期");
        }
        String username = JTokenUtil.get(token,"username");
        SysUser findUser = this.getOne(new QueryWrapper<SysUser>().eq("username",username));
        //根据用户id查找用户角色列表
        List<SysUserRole> userRoles = userRoleService.list(new QueryWrapper<SysUserRole>().eq("uid",findUser.getId()));
        List <SysResource> resources = new ArrayList<>();
        userRoles.forEach(userRole -> {
            //根据用户角色表的角色id列表
            List<SysRoleResource> roleResources = roleResourceService.list(new QueryWrapper<SysRoleResource>().eq("roleId",userRole.getRid()));
            roleResources.forEach(roleResource ->{
                //根据角色得到多个资源表
                List <SysResource> resources1 = resourceService.list(new QueryWrapper<SysResource>().eq("id",roleResource.getResourceId()));
                //定义条件集合,过滤资源表
                List<String> ids = new ArrayList<>();
               resources.forEach(resource->{
                   ids.add(resource.getId());
               });
               if(ids.isEmpty()){
                   resources.addAll(resources1);
               }else{
                   resources1.stream()
                           .filter(s -> !(ids.contains(s.getId())))
                           .collect(Collectors.toList());
                   resources.addAll(resources1);
               }
                });
        });
        //组织成树结构
        List<SysResource> treeList = new ArrayList<>();
        List<Router> routers = new ArrayList<>();
        if(resources.size()>0){
            resources.forEach(item ->{
                //添加一级,layout
                if("0".equals(item.getPid())){
                    treeList.add(item);
                }
            });
        }
         //给爸爸找儿子
        addChildrenToParrent(treeList,resources);
        //准换成路由树
        routers = this.toRouterTree(treeList);

        return  routers;
    }

    @Override
    public void resetPassword(RestPasswordDTO resetPasswordDTO) {

        SysUser findUser = this.getOne(new QueryWrapper<SysUser>().eq("username",resetPasswordDTO.getUsername()));
        if(findUser == null) {
            throw new ServiceException("用户不存在");
        }
        //明文转密文
        String MD5Password = EncryptPassword.ENCRYPT_MD5(resetPasswordDTO.getUsername(),resetPasswordDTO.getNewPassword(),2);
        userService.update(new UpdateWrapper<SysUser>().set("password",MD5Password));
    }

    public void addChildrenToParrent(List<SysResource> operationList,List<SysResource> allResource){
        //得到二级
        operationList.forEach(item ->{
            List<SysResource> children = new ArrayList<>();
            allResource.forEach(r ->{
                if(item.getId().equals(r.getPid())){
                    children.add(r);
                }
            });
            this.addChildrenToParrent(children,allResource);
            item.setChildren(children);
        });
    }

    public List<Router> toRouterTree(List<SysResource> resourceList){
       List<Router> res = new ArrayList<>();
        resourceList.forEach(resource -> {
           Router route = new Router();
           route.setPath(resource.getPath());
           route.setName(resource.getName());
           route.setRedirect(resource.getRedirect());
           route.setComponent(resource.getComponent());
           Map<String,String> meta = new HashMap<>();
           meta.put("title",resource.getTitle());
           meta.put("icon",resource.getIcon());
           route.setMeta(meta);
           List<Router> children = new ArrayList<>();
            if (!resource.getChildren().isEmpty()) {
                children=toRouterTree(resource.getChildren());
            }
            route.setChildren(children);
            res.add(route);
        });
        return res;
    }


}
