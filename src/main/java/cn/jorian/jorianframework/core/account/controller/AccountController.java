package cn.jorian.jorianframework.core.account.controller;

import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.SystemResponse;
import cn.jorian.jorianframework.config.jwt.JToken;
import cn.jorian.jorianframework.core.account.dto.LoginDTO;
import cn.jorian.jorianframework.core.account.service.AccountService;
import cn.jorian.jorianframework.core.system.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "账户管理")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "登录")
    @RequestMapping(method = RequestMethod.POST)
    public SystemResponse login(@RequestBody @Validated @ApiParam(value = "登录数据",required = true) LoginDTO loginDTO){
        accountService.login(loginDTO);
        return new SystemResponse(ResponseCode.SIGN_IN_SUCCESS,((JToken)SecurityUtils.getSubject().getPrincipal()).getToken());
    }

    @ApiOperation(value="登出")
    @RequestMapping(method = RequestMethod.DELETE)
    public SystemResponse logout(){
        return new SystemResponse(ResponseCode.SIGN_OUT_SUCCESS);
    }

    @ApiOperation(value="获取当前用户")
    @RequestMapping(method = RequestMethod.GET)
    public  SystemResponse getCurrentUser(){
        SysUser currentuser = accountService.getCurrentUser();
        return new SystemResponse(ResponseCode.SUCCESS,currentuser);
    }

}
