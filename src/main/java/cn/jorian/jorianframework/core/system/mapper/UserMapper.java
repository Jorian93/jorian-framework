package cn.jorian.jorianframework.core.system.mapper;

import cn.jorian.jorianframework.core.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: jorian
 * @Date: 2019/4/17 10:45
 * @Description:
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<SysUser> {
}
