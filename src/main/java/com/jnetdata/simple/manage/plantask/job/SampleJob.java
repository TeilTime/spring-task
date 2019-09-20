package com.jnetdata.simple.manage.plantask.job;

import com.jnetdata.simple.manage.plantask.DateUtils;
import org.springframework.stereotype.Service;

/**
 * 示例不带参定时任务
 * 
 * @Author Scott
 */
@Service
public class SampleJob {


	public void execute() {

		System.out.println(String.format("普通定时任务A SampleJob !  时间:" + DateUtils.getTimestamp()));
	}
}
