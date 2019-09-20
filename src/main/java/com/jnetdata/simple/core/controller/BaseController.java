package com.jnetdata.simple.core.controller;

import com.jnetdata.simple.base.vo.EntityIdVo;
import com.jnetdata.simple.base.vo.PageVo;
import com.jnetdata.simple.base.vo.PageVo1;
import com.jnetdata.simple.base.vo.PageVo2;
import com.jnetdata.simple.constants.WebPathConstant;
import com.jnetdata.simple.core.model.BaseEntity;
import com.jnetdata.simple.core.model.EntityId;
import com.jnetdata.simple.core.service.BaseService;
import com.jnetdata.simple.core.util.ReflectionUtil;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import org.thenicesys.data.api.Pager;
import org.thenicesys.web.JsonResult;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 * @date 2018/8/28
 */
public class BaseController<IdType extends Serializable, T extends BaseEntity&EntityId<IdType>> extends org.thenicesys.web.BaseController {

    /**
     * 新增
     * @param service
     * @param entity
     * @return
     */
    protected JsonResult<EntityId<IdType>> doAdd(BaseService<T> service, T entity) {

        boolean result = service.insertAllColumn(entity);
        if (result) {
            return JsonResult.renderSuccess(new EntityIdVo(entity.getId()));
        }
        return renderError("新增失败");
    }

    /**
     * 新增
     * @param service
     * @param mail
     * @return
     */
    protected JsonResult<EntityId<IdType>> doAddMail(BaseService<T> service, T mail) {

        boolean result = service.insertAllColumn(mail);
        if (result) {
            return JsonResult.renderSuccess(new EntityIdVo(mail.getId()));
        }
        return renderError("新增失败");
    }


    protected void validateBeforeUpdate(BaseService<T> service, EntityId entity) {

        assertNotEmpty(entity.getId(), "id不能为空");

        T found = service.getById(entity.getId());
        assertNotEmpty(found, "对象必须存在");
    }

    /**
     * 更新
     * @param service
     * @param id
     * @param entity
     * @return
     */
    protected JsonResult<Void> doUpdateById(BaseService<T> service, IdType id, T entity) {

        assertNotEmpty(id, "id不能为空");

        entity.setId(id);
        validateBeforeUpdate(service, entity);

        return service.updateById(entity) ? renderSuccess("更新成功") : renderError("更新失败");
    }

    /**
     * 更新
     * @param service
     * @param id
     * @param entity
     * @return
     */
    protected JsonResult<Void> doUpdateAllColumnById(BaseService<T> service, IdType id, T entity) {

        assertNotEmpty(id, "id不能为空");

        entity.setId(id);
        validateBeforeUpdate(service, entity);

        return service.updateAllColumnById(entity) ? renderSuccess("更新成功") : renderError("更新失败");
    }

    /**
     * 删除
     * @param service
     * @param id
     * @return
     */
    protected JsonResult<Void> doDeleteById(BaseService<T> service, Serializable id) {

        assertNotEmpty(id, "id不能为空");
        return service.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
    }

    /**
     * 删除
     * @param service
     * @param ids
     * @return
     */
    protected JsonResult<Void> doDeleteBatchIds(BaseService<T> service, String ids) {

        return service.deleteBatchIds(convertIds(ids)) ? renderSuccess("删除成功") : renderError("删除失败");
    }

    private Collection<Serializable> convertIds(String ids) {
        return Arrays.stream(ids.split(",")).map(id -> fromStringId(id)).collect(Collectors.toList());
    }
    private IdType fromStringId(String id) {
        Class idTypeClass = getIdTypeClass();
        if (idTypeClass.getName().equals(Integer.class.getName())) {
            return (IdType)Integer.valueOf(id);
        }
        if (idTypeClass.getName().equals(Long.class.getName())) {
            return (IdType)Long.valueOf(id);
        }
        if (idTypeClass.getName().equals(String.class.getName())) {
            return (IdType)id;
        }
        throw new RuntimeException("不支持的Id数据类型");
    }

    /**
     * 查看
     * @param service
     * @param id
     * @return
     */
    protected JsonResult<T> doGetById(BaseService<T> service, Serializable id) {
        assertNotEmpty(id, "id不能为空");
        T found = service.getById(id);
        return (null == found) ? renderError("该对象不存在") : renderSuccess(found);
    }

    /**
     * 分页查询
     * @param service
     * @param pageVo
     * @param template
     * @return
     */
    protected JsonResult<Pager<T>> doList(BaseService<T> service, PageVo pageVo, Object template) {
        Pager<T> page = doSearch(service, pageVo, template);
        return JsonResult.renderSuccess(page);
    }

    protected JsonResult<Pager<T>> doList(BaseService<T> service, PageVo1 pageVo1, Object template) {
        Pager page = doSearch(service, pageVo1, template);
        return JsonResult.renderSuccess(page);
    }

    protected JsonResult<Pager<T>> doList(BaseService<T> service, PageVo2 pageVo2, Object template) {
        Pager page = doSearch(service, pageVo2, template);
        return JsonResult.renderSuccess(page);
    }

    protected JsonResult<Pager<T>> doList(BaseService<T> service, Pager<T> page, Object template) {
        service.search(page, createCondition(template));
        return JsonResult.renderSuccess(page);
    }

    /**
     * 执行查询
     * @param service
     * @param pageVo
     * @param template
     * @return
     */
    protected Pager<T> doSearch(BaseService<T> service, PageVo pageVo, Object template) {
        return doSearch1(service, pageVo, template);
    }

    protected Pager<T> doSearch(BaseService<T> service, PageVo1 pageVo, Object template) {
        return doSearch1(service, pageVo, template);
    }

    protected Pager<T> doSearch(BaseService<T> service, PageVo2 pageVo, Object template) {
        return doSearch1(service, pageVo, template);
    }
    protected<U extends PageVo> Pager<T> doSearch1(BaseService<T> service, U pageVo, Object template) {
        Pager<T> page = createPager(pageVo);
        service.search(page, createCondition(template));
        return page;
    }

    private static void assertNotEmpty(Object id, String message) {
        if (ObjectUtils.isEmpty(id)) {
            throw new NullPointerException(message);
        }
    }
    protected Map<String,Object> createCondition(Object template) {
        return !Objects.isNull(template) ? objectToMap(template) : new HashMap<>();
    }

    @SneakyThrows
    protected Map<String,Object> objectToMap(Object template) {

        Map<String, Object> map = new HashMap<>();
        for (Field field : template.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(template));
        }
        return map;
    }

    private<U extends PageVo> Pager<T> createPager(U pageVo) {
        Pager<T> pager = new Pager<>();
        BeanUtils.copyProperties(pageVo, pager);
        return pager;
    }

    protected boolean isEmpty(String str) {
        return null==str || "".equals(str.trim());
    }

    protected String webPath(String baseUrl, String url) {
        return WebPathConstant.getHtmlFilePath(baseUrl + url);
    }

    private Class getIdTypeClass() {
        return ReflectionUtil.getSuperClassGenricType(getClass(), 0);
    }

}
