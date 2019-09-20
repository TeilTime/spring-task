package com.jnetdata.simple.core.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/21.
 */
public interface EntityId<IdType extends Serializable> {

    IdType getId();

    void setId(IdType id);

}
