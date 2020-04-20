package cn.jorian.jorianframework.core.mail.controller;


import cn.jorian.jorianframework.common.annotation.JLog;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.ResponseResult;
import cn.jorian.jorianframework.core.mail.dto.MailAddDTO;
import cn.jorian.jorianframework.core.mail.dto.MailFindDTO;
import cn.jorian.jorianframework.core.mail.entity.MailTo;
import cn.jorian.jorianframework.core.mail.service.MailService;
import cn.jorian.jorianframework.core.mail.service.MailToService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jorian
 * @since 2019-10-30
 */
@Api(tags = "邮件中心")
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;
    @Autowired
    private MailToService mailToService;

    @JLog("保存邮件")
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "保存邮件")
    public ResponseResult save(@RequestBody @Validated @ApiParam("邮件数据") MailAddDTO mailAddDTO) {
        mailService.saveMail(mailAddDTO);
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取邮件")
    public ResponseResult get(@PathVariable Long id) {
        return new ResponseResult(ResponseCode.SUCCESS,mailService.getById(id));
    }

    @RequestMapping(value = "/{id}/to",method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取邮件发送详情")
    public ResponseResult getMailTo(@PathVariable String id) {
        return new ResponseResult(ResponseCode.SUCCESS,mailToService.list(new QueryWrapper<MailTo>().eq("mailId",id)));
    }

    @GetMapping
    @ApiOperation(value = "邮件列表")
    public ResponseResult list(MailFindDTO mailFindDTO) {
       return new ResponseResult(ResponseCode.SUCCESS,mailService.listMails(mailFindDTO));
    }

}

