package com.jnetdata.simple.manage.plantask.mapper;


import com.jnetdata.simple.manage.plantask.model.QuartzJob;
import com.jnetdata.simple.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface QuartzJobMapper extends BaseMapper<QuartzJob> {

	public List<QuartzJob> findByJobClassName(@Param("jobClassName") String jobClassName);

}
