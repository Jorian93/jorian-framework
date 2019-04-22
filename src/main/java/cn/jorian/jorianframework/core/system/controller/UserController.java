package cn.jorian.jorianframework.core.system.controller;

import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.SystemResponse;
import cn.jorian.jorianframework.core.system.dto.UserAddDTO;
import cn.jorian.jorianframework.core.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 09:24
 * @Description:
 */
@RestController
@RequestMapping("/system")
@Api(value = "用户管理")
public class UserController {
 @Autowired
 private UserService userService;

 @ApiOperation(value = "新增用户")
 @RequestMapping(value = "/user",method = RequestMethod.POST)
 public SystemResponse add(@RequestBody @Validated @ApiParam(value = "用户数据") UserAddDTO addDTO){
  userService.add(addDTO);
  return new SystemResponse(ResponseCode.SUCCESS);
 }

}
