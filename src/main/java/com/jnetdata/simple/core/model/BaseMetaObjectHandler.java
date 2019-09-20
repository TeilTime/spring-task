package com.jnetdata.simple.core.model;

import org.apache.ibatis.reflection.MetaObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/25.
 */
public class BaseMetaObjectHandler<IdType extends Serializable> extends org.thenicesys.mybatis.MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        EntityId<IdType> user = getCurrentUser();

        Date nowDate = getNowDate();

        doInsertFill(metaObject, user, nowDate);
        doUpdateFill(metaObject, user, nowDate);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        doUpdateFill(metaObject, getCurrentUser(), getNowDate());

    }

    protected EntityId<IdType> getCurrentUser() {
        return null;
    }

    protected Date getNowDate() {
        return new Date();
    }

    private void doInsertFill(MetaObject metaObject, EntityId<IdType> user, Date nowDate) {

        if (null==user) {
            return;
        }

        Object createBy = getFieldValByName("createBy", metaObject);
        Object createTime = getFieldValByName("crtime", metaObject);
        if (null == createBy) {
            setFieldValByName("createBy", user.getId(), metaObject);
        }
        if (null == createTime) {
            setFieldValByName("crtime", nowDate, metaObject);
        }
    }

    private void doUpdateFill(MetaObject metaObject, EntityId<IdType> user, Date nowDate) {

        if (null==user) {
            return;
        }

        Object modifyBy = getFieldValByName("modifyBy", metaObject);
        Object modifyTime = getFieldValByName("modifyTime", metaObject);
        if (null == modifyBy) {
            setFieldValByName("modifyBy", user.getId(), metaObject);
        }
        if (null == modifyTime) {
            setFieldValByName("modifyTime", nowDate, metaObject);
        }
    }

}
