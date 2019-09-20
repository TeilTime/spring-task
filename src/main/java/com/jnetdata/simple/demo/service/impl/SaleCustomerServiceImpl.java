package com.jnetdata.simple.demo.service.impl;


import com.jnetdata.simple.core.service.impl.BaseServiceImpl;
import com.jnetdata.simple.demo.mapper.SaleCustomerMapper;
import com.jnetdata.simple.demo.model.SaleCustomer;
import com.jnetdata.simple.demo.service.SaleCustomerService;
import org.springframework.stereotype.Service;
import org.thenicesys.mybatis.impl.PropertyWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author zyj
 * @since 2018-08-29
 */
@Service
public class SaleCustomerServiceImpl extends BaseServiceImpl<SaleCustomerMapper, SaleCustomer> implements SaleCustomerService {

    @Override
    protected PropertyWrapper<SaleCustomer> makeSearchWrapper(Map<String, Object> condition) {
        return createWrapperUtil(condition)
                .eq("id")
                .like("name")
                .getWrapper();
    }

    @Override
    public void delete(String nickName, String description) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("nickName", nickName);
        condition.put("description", description);
        super.delete(createWrapperUtil(condition).getWrapper());
    }
}
