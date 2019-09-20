package com.jnetdata.simple.manage.plantask.service;


import com.baomidou.mybatisplus.service.IService;
import com.jnetdata.simple.core.service.BaseService;
import com.jnetdata.simple.manage.plantask.model.QuartzJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IQuartzJobService extends BaseService<QuartzJob> {

	List<QuartzJob> findByJobClassName(String jobClassName);

	boolean saveAndScheduleJob(QuartzJob quartzJob);

	boolean editAndScheduleJob(QuartzJob quartzJob) throws SchedulerException;

	boolean deleteAndStopJob(QuartzJob quartzJob);
}
