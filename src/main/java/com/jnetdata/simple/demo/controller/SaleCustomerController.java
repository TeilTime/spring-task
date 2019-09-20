package com.jnetdata.simple.demo.controller;

import com.jnetdata.simple.core.controller.BaseController;
import com.jnetdata.simple.core.model.EntityId;
import com.jnetdata.simple.demo.model.SaleCustomer;
import com.jnetdata.simple.demo.service.SaleCustomerService;
import com.jnetdata.simple.demo.vo.SaleCustomerVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.thenicesys.data.api.Pager;
import org.thenicesys.web.JsonResult;

/**
 * SpringMvc映射测试
 * 中文， html ajax调用, app调用
 * 注意： func(@RequestBody)这种映射不支持GetMapping
 * @author zyj
 * @since 2018-08-29
 */
@Controller
@Slf4j
@RequestMapping("/demo/salecustomer")
public class SaleCustomerController extends BaseController<Long, SaleCustomer> {

    @Autowired
    private SaleCustomerService saleCustomerService;

    private final static String BASEURL = "/demo/salecustomer";

    /**
     * 执行新增操作
     * @param entity
     * @return
     */
    @PostMapping
    @ResponseBody
    public JsonResult<EntityId<Long>> add(@Validated @RequestBody SaleCustomer entity) {
        return doAdd(saleCustomerService, entity);
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public JsonResult<Void> deleteById(@PathVariable("id") Long id) {
        return doDeleteById(saleCustomerService, id);
    }

    /**
     * 执行批量删除操作
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}/batch")
    @ResponseBody
    public JsonResult<Void> deleteBatchIds(@PathVariable("ids") String ids) {
        return doDeleteBatchIds(saleCustomerService, ids);
    }

    /**
     * 属性选择性更新操作
     * @param id
     * @param entity
     * @return
     */
    @ApiOperation(value = "选择性更新操作", notes="只更新entity中设置为非null的属性")
    @PutMapping("/{id}")
    @ResponseBody
    public JsonResult<Void> updateById(@PathVariable("id") Long id, @RequestBody SaleCustomer entity) {
        return doUpdateById(saleCustomerService, id, entity);
    }

    /**
     * 全部属性更新操作
     * @param id
     * @param entity
     * @return
     */
    @ApiOperation(value = "执行修改操作", notes="注意必须提供实体的所有属性否则没有提供的属性将被设置为null")
    @PutMapping("/{id}/allColumn")
    @ResponseBody
    public JsonResult<Void> doUpdateAllColumnById(@PathVariable("id") Long id, @RequestBody SaleCustomer entity) {
        return doUpdateAllColumnById(saleCustomerService, id, entity);
    }

    @ApiOperation(value = "查看", notes = "查看指定id的实体对象")
    @GetMapping("/{id}")
    @ResponseBody
    public JsonResult<SaleCustomer> getById(@PathVariable("id") Long id) {
        return doGetById(getService(), id);
    }

    /**
     * 跳转到查询页面
     * @return
     */
    @GetMapping(value = "/index")
    public String toList() {
        return pageFilePath("/list");
    }

    @ApiOperation(value = "查询", notes="根据vo指定条件进行查询")
    @PostMapping(value = "/list")
    @ResponseBody
    public JsonResult<Pager<SaleCustomer>> list(@RequestBody SaleCustomerVo vo) {
        return doList(getService(), vo.getPager(), vo.getEntity());
    }


    private SaleCustomerService getService() {
        return this.saleCustomerService;
    }

    private String pageFilePath(String url) {
        return super.webPath(BASEURL, url);
    }

}

