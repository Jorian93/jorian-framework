package cn.jorian.jorianframework.core.file.controller;

import cn.jorian.jorianframework.common.model.PicUploadResult;
import cn.jorian.jorianframework.common.response.SystemResponse;
import cn.jorian.jorianframework.core.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: jorian
 * @Date: 2019/5/1 09:25
 * @Description:
 */
@RestController
@RequestMapping("fileUpload")
@Api(tags="文件管理")
public class FileController{
    @Autowired
    private FileService fileService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "文件上传")
    public SystemResponse uploadFile(MultipartFile file){
        PicUploadResult picUploadResult = fileService.save(file);
        return new SystemResponse(picUploadResult.getError(),picUploadResult.getMsg(),picUploadResult);
    }
}
