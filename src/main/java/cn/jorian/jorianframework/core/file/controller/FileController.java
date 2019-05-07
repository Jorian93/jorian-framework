package cn.jorian.jorianframework.core.file.controller;

import cn.jorian.jorianframework.common.model.PicUploadResult;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.common.response.SystemResponse;
import cn.jorian.jorianframework.core.file.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Auther: jorian
 * @Date: 2019/5/1 09:25
 * @Description:
 */
@RestController
@RequestMapping("fileUpload")
public class FileController{
    @Autowired
    private FileService fileService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "文件上传")
    public SystemResponse uploadFile(MultipartFile file){
        return new SystemResponse(fileService.save(file).getError(),ResponseCode.SUCCESS.msg,fileService.save(file));
    }
}
