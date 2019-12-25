package cn.jorian.jorianframework.core.elsearch.service;

import cn.jorian.jorianframework.core.system.entity.SysUser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: jorian
 * @date: 2019/12/3 20:20
 * @description: this is  description for the class
 */
public interface SearchUserService {

    /**
     * 通过responstory
     * @param q
     * @return
     */
    List<SysUser> searchOne(String q);

    /**
     * 通过searchTemplate方式实现
     * @return
     */
    List<Map<String, Object>> searchAll();

    /**
     * 通过jest方式实现
     * @return
     */
    String searchByJest() throws IOException;

    String add(SysUser sysUser) throws IOException;

}
