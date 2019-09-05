package com.jnetdata.simple.manage.plantask.service.impl;

import com.jnetdata.simple.core.service.impl.BaseServiceImpl;
import com.jnetdata.simple.manage.plantask.mapper.PlanTaskMapper;
import com.jnetdata.simple.manage.plantask.model.PlanTask;
import com.jnetdata.simple.manage.plantask.service.PlanTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thenicesys.mybatis.impl.PropertyWrapper;

import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PlanTaskServiceImpl extends BaseServiceImpl<PlanTaskMapper,PlanTask> implements PlanTaskService {
    @Override
    protected PropertyWrapper<PlanTask> makeSearchWrapper(Map<String, Object> condition) {
        return createWrapperUtil(condition).like("name").getWrapper();
    }

    /**
     * 不分页查询
     * @param planTask
     * @return
     */
    @Override
    public List<PlanTask> getPlanTask(PlanTask planTask) {
        return super.list(new PropertyWrapper<>(PlanTask.class).eq("tasktype",planTask.getTasktype()).like("name",planTask.getName()));
    }
}
