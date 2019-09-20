package com.jnetdata.simple.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class PageVo2 extends PageVo1 {

    @ApiModelProperty(value = "总记录数", example = "100")
    private Integer total;

}
