package com.jnetdata.simple.demo.service;


import com.jnetdata.simple.core.service.BaseService;
import com.jnetdata.simple.demo.model.SaleCustomer;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author zyj
 * @since 2018-08-29
 */
public interface SaleCustomerService extends BaseService<SaleCustomer> {

    /**
     * 通过昵称和描述删除(注意这个函数只是为了测试接口， 正常情况下不能用这种方式删除!
     * @param nickName
     * @param description
     */
    @Deprecated
    void delete(String nickName, String description);

}
