package cn.jorian.jorianframework.core.account.service.impl;

import cn.jorian.jorianframework.common.exception.ServiceException;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.utils.JTokenUtil;
import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.core.account.dto.LoginDTO;
import cn.jorian.jorianframework.core.account.service.AccountService;

import cn.jorian.jorianframework.core.system.entity.SysUser;
import cn.jorian.jorianframework.core.system.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Auther: jorian
 * @Date: 2019/4/18 14:08
 * @Description:
 */
@Service
public class AccountServiceImpl  extends ServiceImpl<UserMapper,SysUser> implements AccountService {
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
        return findUser;
    }
}
