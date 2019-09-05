package com.jnetdata.simple.manage.plantask.service;

import com.jnetdata.simple.core.service.BaseService;
import com.jnetdata.simple.manage.plantask.model.PlanTask;

import java.util.List;

public interface PlanTaskService extends BaseService<PlanTask> {

    List<PlanTask> getPlanTask(PlanTask planTask);
}
