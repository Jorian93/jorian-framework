package cn.jorian.jorianframework.core.file.service;

import cn.jorian.jorianframework.common.model.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: jorian
 * @Date: 2019/5/1 09:27
 * @Description:
 */
public interface FileService {
    PicUploadResult save(MultipartFile uploadFile);
}
