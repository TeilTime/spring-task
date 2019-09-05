package com.jnetdata.simple.manage.plantask.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jnetdata.simple.core.model.BaseEntity;
import com.jnetdata.simple.core.model.EntityId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.thenicesys.fastjson.serializer.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 定时任务在线管理
 * @Author: jeecg-boot
 * @Date:  2019-01-02
 * @Version: V1.0
 */
@Data
@TableName("sys_quartz_job")
@ApiModel(description = "计划任务调度")
public class QuartzJob  extends BaseEntity implements EntityId<Long> {

	@ApiModelProperty(value = "id",hidden = true)
	@TableId(value = "id",type = com.baomidou.mybatisplus.enums.IdType.ID_WORKER)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

/*	@TableField(value = "create_by")
	@NotEmpty(message = "创建人")
	@ApiModelProperty(value = "创建人")
	private String createBy;*/

	@TableField(value = "CRTIME")
	@NotEmpty(message = "创建时间")
	@ApiModelProperty(value = "创建时间")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@TableField(value = "del_flag")
	@NotEmpty(message = "删除状态")
	@ApiModelProperty(value = "删除状态")
	private Integer delFlag;

	/*@TableField(value = "modify_by")
	@NotEmpty(message = "修改人")
	@ApiModelProperty(value = "修改人")
	private String updateBy;

	@TableField(value = "modify_time")
	@NotEmpty(message = "修改时间")
	@ApiModelProperty(value = "修改时间")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;*/

	@TableField(value = "job_class_name")
	@NotEmpty(message = "任务类名")
	@ApiModelProperty(value = "任务类名")
	private String jobClassName;

	@TableField(value = "old_job_class_name")
	@NotEmpty(message = "任务类名")
	@ApiModelProperty(value = "任务类名")
	private String oldjobClassName;

	@TableField(value = "cron_expression")
	@NotEmpty(message = "cron表达式")
	@ApiModelProperty(value = "cron表达式")
	private String cronExpression;

	@TableField(value = "parameter")
	@NotEmpty(message = "参数")
	@ApiModelProperty(value = "参数")
	private String parameter;

	@TableField(value = "description")
	@NotEmpty(message = "描述")
	@ApiModelProperty(value = "描述")
	private String description;

	@TableField(value = "status")
	@NotEmpty(message = "状态 0正常 -1停止")
	@ApiModelProperty(value = "状态 0正常 -1停止")
	private Integer status;

}
