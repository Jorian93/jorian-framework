package cn.jorian.jorianframework.core.system.service;

import cn.jorian.jorianframework.core.system.dto.ResourceAddDTO;
import cn.jorian.jorianframework.core.system.dto.ResourceFindDTO;
import cn.jorian.jorianframework.core.system.entity.SysResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: jorian
 * @Date: 2019/4/17 10:50
 * @Description:
 */
public interface ResourceService extends IService<SysResource> {

    /**
     * 新增
     */
    void add(ResourceAddDTO userAddDTO);
    /**
     * 删除
     */
    void delete(String id);
    /**
     * 更新
     */
    void update(SysResource sysResource);

    /**
     * 获取资源列表
     * @return
     */
    IPage<SysResource> getList(ResourceFindDTO resourceFindDTO);
    /**
     * 获取资源树
     * @return
     */
    List<SysResource> getTree();

    List<String> getUserTree(String rid);

}
