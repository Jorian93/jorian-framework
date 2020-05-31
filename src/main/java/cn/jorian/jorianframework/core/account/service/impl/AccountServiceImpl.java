package cn.jorian.jorianframework.core.account.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.model.Dict;
import cn.jorian.jorianframework.common.model.LoginUser;
import cn.jorian.jorianframework.common.model.RedisDB;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.RedisTool;
import cn.jorian.jorianframework.common.utils.ToolEncryptPassword;
import cn.jorian.jorianframework.common.utils.ToolJWT;
import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.config.shiro.UserRealm;
import cn.jorian.jorianframework.core.account.dto.RestPasswordDTO;
import cn.jorian.jorianframework.core.account.dto.Router;
import cn.jorian.jorianframework.core.account.dto.UsernamePasswordDTO;
import cn.jorian.jorianframework.core.account.service.AccountService;
import cn.jorian.jorianframework.core.system.entity.*;
import cn.jorian.jorianframework.core.system.mapper.UserMapper;
import cn.jorian.jorianframework.core.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: jorian
 * @Date: 2019/4/18 14:08
 * @Description:
 */
@Slf4j
@Service
@Transactional
public class AccountServiceImpl extends ServiceImpl<UserMapper, SysUser> implements AccountService {

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleResourceService roleResourceService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    StringRedisTemplate strRedisTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    UserRealm userRealm;

    /**
     * 用户名密码登录
     */
    @Override
    public String login(UsernamePasswordDTO usernamePasswordDTO) {
        if(StringUtils.isEmpty(usernamePasswordDTO)){
        }
        if( "".equals(usernamePasswordDTO.getUsername()) || "".equals(usernamePasswordDTO.getPassword()) ){
            throw new ServiceException(ResponseCode.SIGN_IN_USERNAME_PASSWORD_EMPTY.msg,-1);
        }
        //封装token进行登录认证，（token=null，账号，密码）
        JToken token = new JToken(null, usernamePasswordDTO.getUsername(), usernamePasswordDTO.getPassword());

        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            if(!subject.isAuthenticated()){
                throw new ServiceException(ResponseCode.TOKEN_AUTHENTICATION_FAIL);
            }
        }catch (DisabledAccountException e){
            throw new ServiceException(e.getMessage(), ResponseCode.SIGN_IN_FAIL.code,e);

        }catch (Exception e){
            throw new ServiceException(ResponseCode.SIGN_IN_FAIL.msg,e);
        }
        //登陆完成，返回token
        String jwt = ((JToken)SecurityUtils.getSubject().getPrincipal()).getJwt();
        // redis中存一份，控制令牌有效期
        token.setJwt(jwt);
        token.setExpiredAt(System.currentTimeMillis()+JToken.EXPIRE_TIME*1000); //设置过期时间点
        // 缓存一个user
        LoginUser loginuser = new LoginUser();
        BeanUtils.copyProperties(token, loginuser);
        String dbkey = RedisDB.getDBKey(RedisDB.TABLE_USER) + ToolJWT.get(jwt, "uid");
        redisTemplate.opsForValue().set(dbkey, loginuser);
        if(JToken.EXPIRE_TIME > 0){
            redisTemplate.expire(dbkey, JToken.EXPIRE_TIME, TimeUnit.SECONDS);
        }      
        return jwt;
    }

    @Override
    public String wxlogin(String code) {

        String APPID = "wx293d2f24985114b6";
        String SECRET = "435cb56d5e37dd6f0581f9cd4fba2d5b";
        String CODE = code;
        String uri = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+SECRET+"&js_code="+CODE+"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.getForEntity(uri,String.class);

        String str = res.toString();

        //oqy4C5TT1Gew-i7d98YWGbw5jFQQ
        //提出openid
        String [] strings = str.split(",");
        String openids = strings[2];
        String [] openidm = openids.split(":");
        String openid = openidm[1];
        String oid = openid.substring(1,openid.length()-2);

        return  oid;
    }

    @Override
    public SysUser getCurrentUser() {
        JToken token = null;
         try{
            token = (JToken)SecurityUtils.getSubject().getPrincipal();
         }catch (Exception e){
             throw new ServiceException(ResponseCode.TOKEN_EXPIRED);
         }
         if(token == null){
             throw new ServiceException(ResponseCode.TOKEN_AUTHENTICATION_FAIL);
         }
        SysUser findUser = this.getOne(new QueryWrapper<SysUser>().eq("username",token.getUsername()));
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

    /**
     * 
     */
    @Override
    public List<Router> getCurrentUserResource() {

        JToken token = null;
        try{
           token = (JToken)SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
            throw new ServiceException(ResponseCode.TOKEN_EXPIRED);
        }
        if(token == null){
            throw new ServiceException(ResponseCode.TOKEN_AUTHENTICATION_FAIL);
        }
        String username = token.getUsername();
        SysUser findUser = this.getOne(new QueryWrapper<SysUser>().eq("username",username).select("id,username,status,password"));
        log.info("当前用户id："+findUser.getId());
        //根据用户id查找用户角色列表
        List<SysUserRole> userRoles = userRoleService.list(new QueryWrapper<SysUserRole>().eq("uid",findUser.getId()));
        List <SysResource> resources = new ArrayList<>();
       //遍历用户角色list，根据角色id，拿到所有的角色资源list
        userRoles.stream().forEach(userRole -> {
            List<SysRoleResource> roleResources = roleResourceService.list(new QueryWrapper<SysRoleResource>()
                    .eq("roleId",userRole.getRid()));
            if(!roleResources.isEmpty()){
                //遍历角色资源，获得资源详情
                roleResources.stream().forEach(roleResource -> {
                    //根据角色得到多个资源表,只要菜单类型
                    SysResource resourcesItem = resourceService.getOne(new QueryWrapper<SysResource>()
                            .eq("id", roleResource.getResourceId())
                            .eq("type", Dict.RESOURCE_MENU.key));
                    //当前资源未包含则添加
                    if(resources.isEmpty() || !resources.contains(resourcesItem) && resourcesItem != null){
                        resources.add(resourcesItem);
                    }
                });
            }
        });

        if(!resources.isEmpty()){
            //组织成树结构
            List<SysResource> treeList = new ArrayList<>();
            for(SysResource sysResource : resources){
                //添加一级,layout
                if(sysResource != null && "0".equals(sysResource.getPid())){
                    treeList.add(sysResource);
                }
            }
            //给爸爸找儿子
            treeList = this.addChildrenToParrent(treeList,resources);
            //转换成路由树
            List<Router> routers = this.toRouterTree(treeList);
            return  routers;
        }
      return null;
    }

    @Override
    public void resetPassword(RestPasswordDTO resetPasswordDTO) {
        SysUser findUser = this.getOne(new QueryWrapper<SysUser>()
        .eq("username",resetPasswordDTO.getUsername()));
        if(findUser == null) {
            throw new ServiceException("用户不存在",-1);
        }
        //明文转密文
        String MD5Password = ToolEncryptPassword.ENCRYPT_MD5(resetPasswordDTO.getUsername(),resetPasswordDTO.getNewPassword());
        userService.update(new UpdateWrapper<SysUser>().eq("username",findUser.getUsername()).set("password",MD5Password));
    }


    /**
     * 顶级树找儿子
     * @param operationList 顶级树
     * @param allResource 资源池
     */
    @Override
    public List<SysResource> addChildrenToParrent(List<SysResource> operationList, List<SysResource> allResource){
        if(allResource == null || operationList == null){
            throw new ServiceException("用户未绑定角色，或角色未配置权限",-1);
        }
        //得到二级
        operationList.forEach(item ->{
            List<SysResource> children = new ArrayList<>();
            allResource.forEach(r ->{
                if(item != null && r != null){
                    if(item.getId().equals(r.getPid())){
                        children.add(r);
                    }
                }

            });
            this.addChildrenToParrent(children,allResource);
            item.setChildren(children);
        });
        return operationList;
    }

    /**
     * 将树形结构整理为element框架渲染需要的字段
     * @param resourceList
     * @return
     */
    @Override
    public List<Router> toRouterTree(List<SysResource> resourceList){
       List<Router> res = new ArrayList<>();
        resourceList.forEach(resource -> {
           Router route = new Router();
           route.setPath(resource.getPath());
           route.setName(resource.getName());
           route.setRedirect(resource.getRedirect());
           route.setComponent(resource.getComponent());
           Map<String,String> meta = new HashMap<>();
           route.setHidden(resource.getHidden());
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
        log.info("用户菜单拉取完成");
        return res;

    }

    @Override
    public void logOut() {
        
        //2.清理redis/
        JToken jtoken = (JToken)SecurityUtils.getSubject().getPrincipal();
        String dbkey = RedisDB.getDBKey(RedisDB.TABLE_USER) + ToolJWT.get(jtoken.getJwt(),"uid");
        redisTemplate.delete(dbkey);
        //1.清理shiro
        //userRealm.clearAllCache();
    }


}
