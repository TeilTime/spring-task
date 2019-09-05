package com.jnetdata.simple.manage.plantask.vo;

import com.jnetdata.simple.base.vo.PageVo1;
import com.jnetdata.simple.manage.plantask.model.PlanTask;
import lombok.Data;

@Data
public class PlanTaskVo {
    private PageVo1 pager;

    private PlanTask entity;
}
