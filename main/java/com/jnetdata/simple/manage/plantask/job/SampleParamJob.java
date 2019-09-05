package com.jnetdata.simple.manage.plantask.job;

import com.jnetdata.simple.manage.plantask.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SampleParamJob{

	public void execute() {

		System.out.println(String.format("普通定时任务B SampleJob !  时间:" + DateUtils.getTimestamp()));
	}
}
