package com.jnetdata.simple.demo.controller;

import com.jnetdata.simple.base.vo.PageVo;
import com.jnetdata.simple.core.controller.BaseController;
import com.jnetdata.simple.demo.model.SaleCustomer;
import com.jnetdata.simple.demo.service.SaleCustomerService;
import com.jnetdata.simple.demo.vo.SaleCustomerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thenicesys.web.JsonResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@Api(value = "DemoSaleCustomerController", description = "演示接口调用")
@RequestMapping("/demo/demosalecustomer")
public class DemoSaleCustomerController extends BaseController {

    @Autowired
    private SaleCustomerService saleCustomerService;

    private final static String BASEURL = "/demo/salecustomer";

    @GetMapping(value = "/toEcho")
    public String toEcho() {
        return webPath("/toEcho");
    }

    /**
     * 请求参数(特别测试中文)
     * @param name
     * @return
     */
    @ApiOperation(value="echo1", notes="请求参数(特别测试中文)", httpMethod = "GET,POST")
    @RequestMapping(value = "/echo1", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String echo1(@RequestParam("name") String name) {
        return name;
    }

    /**
     * 请求体
     * @param entity
     * @return
     */
    @RequestMapping(value = "/echo2", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult echo2(@Validated @RequestBody SaleCustomer entity) {
        return JsonResult.renderSuccess(entity);
    }


    /**
     * 请求体(复合对象)
     * @param vo
     * @return
     */
    @RequestMapping(value = "/echo3", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult echo3(@Validated @RequestBody SaleCustomerVo vo) {
        return JsonResult.renderSuccess(vo);
    }

    /**
     * 请求路径和请求体(复合对象)
     * @param id
     * @param vo
     * @return
     */
    @RequestMapping(value = "/echo4/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult echo4(@PathVariable("id") Long id, @Validated @RequestBody SaleCustomerVo vo) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("vo", vo);
        return JsonResult.renderSuccess(map);
    }

    /**
     * 请求参数和请求体
     * @param pager 分页
     * @param entity 实体对象
     * @return
     */
    @RequestMapping(value = "/echo5", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult echo5(@ModelAttribute("pager") PageVo pager, @Validated @RequestBody SaleCustomer entity) {
        Map<String,Object> map = new HashMap<>();
        map.put("pager", pager);
        map.put("entity", entity);
        return JsonResult.renderSuccess(map);
    }


    /**
     * 请求体
     * @param entity
     * @return
     */
    @RequestMapping(value = "/echo6", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult echo6(@Validated @ModelAttribute("entity") SaleCustomer entity) {
        return JsonResult.renderSuccess(entity);
    }

    /**
     * 请求体(复合体)
     * @param vo
     * @return
     */
    @RequestMapping(value = "/echo7", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult echo7(@Validated @ModelAttribute("vo") SaleCustomerVo vo) {
        return JsonResult.renderSuccess(vo);
    }

    private SaleCustomerService getService() {
        return this.saleCustomerService;
    }

    private String webPath(String url) {
        return super.webPath(BASEURL, url);
    }

}
