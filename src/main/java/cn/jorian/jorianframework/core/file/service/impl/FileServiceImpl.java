package cn.jorian.jorianframework.core.file.service.impl;

import cn.jorian.jorianframework.common.model.PicUploadResult;
import cn.jorian.jorianframework.common.response.ResponseCode;
import cn.jorian.jorianframework.core.file.dto.FileFindDTO;
import cn.jorian.jorianframework.core.file.entity.MyFile;
import cn.jorian.jorianframework.core.file.mapper.FileMapper;
import cn.jorian.jorianframework.core.file.service.FileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * @Author: jorian
 * @Date: 2019/5/1 09:28
 * @Description:
 */
@Service
@Data
public class FileServiceImpl extends ServiceImpl<FileMapper, MyFile> implements FileService {

    @Value("${files.path}")
    private String savePath;

    @Value("${files.url}")
    private String urlPath;

    @Override
    public PicUploadResult save(MultipartFile uploadFile) {
    PicUploadResult result = new PicUploadResult();
    //获取文件全名  1.jpg
    String fileName = uploadFile.getOriginalFilename();

    //获取图片类型   .jpg
    String type = fileName.substring(fileName.lastIndexOf("."));

    //判断是否为正确的图片类型
	if(!type.matches("^.*(jpeg|jpg|png|gif)$")){
        //表明图片的格式不正确
        result.setError(ResponseCode.UPLOAD_FAIL.code);
        result.setMsg("图片的格式不正确");
        return result;
    }
    //生成文件信息
    MyFile myFile = new MyFile();
	myFile.setContentType(type);
    myFile.setSize((int) uploadFile.getSize());
	try {
        //判断是否为恶意文件
        BufferedImage image = ImageIO.read(uploadFile.getInputStream());
        int height = image.getHeight();
        int width = image.getWidth();

        //判断是否为一个正确的图片
        if(height ==0 || width ==0){
            //表示不是一张图片
            result.setError(ResponseCode.UPLOAD_FAIL.code);
            result.setMsg("图片非法");
            return result;
        }

        /**
         * 1.如果程序执行到这里,表示上传的图片是一个正确的格式 则进行如果操作
         * 2.准备磁盘路径和访问路径
         *
         */
        result.setHeight(height+"");
        result.setWidth(width+"");

        //String localDir = "E:/jt-upload/";
        //String urlPath = "http://image.jt.com/";

        //定义时间格式并且转化为yyyy/MM/dd
        String datePath = new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

        //生成三位随机数				0-899 + 100 =100-999
        int randomNum = new Random().nextInt(900) + 100;

        //形成url访问路径 image.com   /yyyy/MM/dd/HH/123456.jpg
        String imageUrl = urlPath + "/"+datePath + "/"+randomNum + fileName;
        result.setUrl(imageUrl);
        myFile.setUrl(imageUrl);
        //形成本地磁盘路径
        String localPath = savePath + "/"+ datePath;
        myFile.setPath(localPath+"/" + randomNum+fileName);
        //生成文件夹目录
        File file = new File(localPath);

        //判断文件是否存在,如果不存在需要创建文件夹
        if(!file.exists()){
            file.mkdirs();
        }

        //如果文件夹存在则执行些写盘操作
        uploadFile.transferTo(new File(localPath+"/" + randomNum+fileName));


    } catch (Exception e) {
        e.printStackTrace();
        //表示文件上传失败
        result.setError(ResponseCode.UPLOAD_FAIL.code);
        result.setMsg("网络错误！");
        return result;
    }
        //成功
        result.setError(ResponseCode.UPLOAD_SUCCESS.code);
        //保存信息
        myFile.setCreateTime(LocalDateTime.now());
        myFile.setUpdateTime(LocalDateTime.now());
        myFile.setType(0);
        this.save(myFile);

		return result;
}

    @Override
    public IPage<MyFile> getList(FileFindDTO findDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        return this.page(new Page<>(findDTO.getPage(),findDTO.getLimit()),queryWrapper);
    }
}
