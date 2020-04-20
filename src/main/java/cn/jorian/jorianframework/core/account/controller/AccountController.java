package cn.jorian.jorianframework.core.account.controller;

import cn.jorian.jorianframework.common.annotation.JLog;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
import cn.jorian.jorianframework.core.account.dto.UsernamePasswordDTO;
import cn.jorian.jorianframework.core.account.dto.RestPasswordDTO;
import cn.jorian.jorianframework.core.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jorian
 * 前端控制器
 */
@RestController
@Api(tags = "账户管理")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "登入系统")
    @RequestMapping(method = RequestMethod.POST)
    @JLog("登入系统")
    public ResponseResult login(@RequestBody @Validated @ApiParam(value = "登录数据",required = true) UsernamePasswordDTO loginDTO){
        return new ResponseResult(ResponseCode.SIGN_IN_SUCCESS,accountService.login(loginDTO));
    }

    @ApiOperation(value="登出系统")
    @RequestMapping(method = RequestMethod.DELETE)
    @JLog("登出系统")
    public ResponseResult logout(){
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @ApiOperation(value="重置密码")
    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    @JLog("重置密码")
    public ResponseResult resetPassword(@RequestBody @Validated @ApiParam(value = "新旧密码数据",required = true) RestPasswordDTO resetPasswordDTO){
        accountService.resetPassword(resetPasswordDTO);
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @ApiOperation(value="获取当前用户")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseResult getCurrentUser(){
        return new ResponseResult(ResponseCode.SUCCESS,accountService.getCurrentUser());
    }

    @ApiOperation(value="获取当前用户路由表")
    @RequestMapping(value="/routers",method = RequestMethod.GET)
    public ResponseResult getCurrentUserResource(){
        return new ResponseResult(ResponseCode.SUCCESS, accountService.getCurrentUserResource());
    }

}
