package cn.jorian.jorianframework.core.job.mapper;


import cn.jorian.jorianframework.core.job.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author jorian
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into t_job(jobName, description, cron, springBeanName, methodName, isSysJob, status, createTime, updateTime) values(#{jobName}, #{description}, #{cron}, #{springBeanName}, #{methodName}, #{isSysJob}, 1, now(), now())")
	int save(Job job);

	@Select("select * from t_job t where t.id = #{id}")
	Job getById(Long id);

	@Select("select * from t_job t where t.jobName = #{jobName}")
	Job getByName(String jobName);

	int update(Job job);

	int count(@Param("params") Map<String, Object> params);

	List<Job> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                   @Param("limit") Integer limit);
}
