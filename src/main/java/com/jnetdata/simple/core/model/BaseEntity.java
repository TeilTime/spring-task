package com.jnetdata.simple.core.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Administrator
 * @date 2018/8/28
 */
@Data
@ApiModel("BaseEntity")
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人名称
     */
    @TableField(value="CRUSER", fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建人名称(系统会自动维护）", hidden = true)
    private String crUser;


    /**
     * 创建人id
     */
    @TableField(value="CRNUMBER", fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建人(系统会自动维护）", hidden = true)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value="CRTIME", fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间(系统会自动维护）", hidden = true)
    @JSONField(format = "yyyy-MM-dd  HH:mm:ss")
    private Date createTime;

    /**
     *  最后修改人id
     */
    @TableField(value="modify_by",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="最后修改人(系统会自动维护）", hidden = true)
    private Long modifyBy;

    /**
     * 最后修改时间
     */
    @TableField(value="modify_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="最后修改时间(系统会自动维护）", hidden = true)
    @JSONField(format = "yyyy-MM-dd  HH:mm:ss")
    private Date modifyTime;

    /*@Version
    private Long version;
    */

    public static String formatTime(Date dateTime) {
        return formatTime(dateTime, "yyyy-MM-dd HH:mm:ss");
    }
    public static String formatDate(Date dateTime) {
        return formatTime(dateTime, "yyyy-MM-dd");
    }

    public static String formatTime(Date dateTime, String pattern) {
        if (Objects.isNull(dateTime)) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(dateTime);
        return dateString;
    }


}
