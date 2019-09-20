package com.jnetdata.simple.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.thenicesys.data.api.Pair;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageVo1 extends PageVo {

    @ApiModelProperty(value = "排序")
    private List<Pair<String, Boolean>> sortProps = new ArrayList();

    public int getCurrNum(){
        return (getCurrent()-1)*getSize();
    }
}
