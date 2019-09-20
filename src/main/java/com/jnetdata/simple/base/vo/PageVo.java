package com.jnetdata.simple.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页", example = "1")
    private Integer current;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer size;

    public PageVo() {

    }
    public PageVo(Integer current, Integer size) {
        this.current = current;
        this.size = size;
    }

}
