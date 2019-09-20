package com.jnetdata.simple.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jnetdata.simple.core.model.BaseEntity;
import com.jnetdata.simple.core.model.EntityId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.thenicesys.fastjson.serializer.ToStringSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author zyj
 * @since 2018-08-29
 */
@ApiModel(value = "SaleCustomer", description = "销售客户")
@Data
@TableName("t_sale_customer")
public class SaleCustomer extends BaseEntity implements EntityId<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    @TableId(value = "id", type = com.baomidou.mybatisplus.enums.IdType.ID_WORKER)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "客户名称", example = "zhanshan")
    @NotEmpty(message="名字不能为空")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "昵称", example = "小三")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "客户电话号码", example = "18611801111")
    @TableField("mobilephone")
    private String mobilephone;

    @ApiModelProperty(value = "描述", example = "这是一个描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "年龄", example = "30")
    @TableField("age")
    private Integer age;

    @ApiModelProperty(value = "体重(Kg)", example = "75.65")
    @TableField("weight")
    private Double weight;

    @ApiModelProperty(value = "每月收入（元)", example = "8257.65")
    @TableField("salary")
    private BigDecimal salary;

    @ApiModelProperty(value = "生日", example = "1995/01/07 10:35:00")
    @TableField("birth_date")
    @JSONField(format="yyyy-MM-dd")
    private Date birthDate;

    /**
     * 界面生日查询使用， 不进行数据库持久化
     */
    @ApiModelProperty(value = "生日", allowableValues = "range('1900/01/01 00:00:00', '3000/01/01 00:00:00')", example = "2019/01/07 10:35:00")
    @TableField(exist = false)
    @JSONField(format="yyyy-MM-dd")
    private Date endBirthDate;

    public String getFormatBirthDate() {
        return formatDate(birthDate);
    }

}
