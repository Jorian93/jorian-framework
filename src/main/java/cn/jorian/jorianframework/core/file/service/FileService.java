package cn.jorian.jorianframework.core.file.service;

import cn.jorian.jorianframework.common.model.PicUploadResult;
import cn.jorian.jorianframework.core.file.dto.FileFindDTO;
import cn.jorian.jorianframework.core.file.entity.MyFile;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: jorian
 * @Date: 2019/5/1 09:27
 * @Description:
 */
public interface FileService extends IService<MyFile> {
    /**
     * 保存文件及信息
     * @param uploadFile
     * @return
     */
    PicUploadResult save(MultipartFile uploadFile);

    /**
     * 查询文件列表
     * @param findDTO
     * @return
     */
    IPage<MyFile> getList(FileFindDTO findDTO);
}
