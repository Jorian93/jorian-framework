package cn.jorian.jorianframework.core.system.mapper;

import cn.jorian.jorianframework.core.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 14:23
 * @Description:
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<SysRole> {
}
