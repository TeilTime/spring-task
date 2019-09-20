package com.jnetdata.simple.core.vo;

import com.jnetdata.simple.base.vo.PageVo;
import com.jnetdata.simple.core.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Vo对象")
public class BaseEntityVo<Page extends PageVo, T extends BaseEntity> {

    @ApiModelProperty(value = "分页")
    private Page pager;

    @ApiModelProperty(value = "实体")
    private T entity;

}
