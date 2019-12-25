package cn.jorian.jorianframework.core.elsearch.mapper;


import cn.jorian.jorianframework.core.system.entity.SysUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: jorian
 * @date: 2019/11/20 14:58
 * @description: this is  description for the class
 */
public interface SearchSysUserMapper extends ElasticsearchRepository<SysUser,String> {
}
