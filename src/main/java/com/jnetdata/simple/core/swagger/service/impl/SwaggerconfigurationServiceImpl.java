package com.jnetdata.simple.core.swagger.service.impl;

import com.jnetdata.simple.core.service.impl.BaseServiceImpl;
import com.jnetdata.simple.core.swagger.mapper.SwaggerconfigurationMapper;
import com.jnetdata.simple.core.swagger.model.Swaggerconfiguration;
import com.jnetdata.simple.core.swagger.service.SwaggerconfigurationService;
import org.springframework.stereotype.Service;
import org.thenicesys.mybatis.impl.PropertyWrapper;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyj
 * @since 2019-09-10
 */
@Service
public class SwaggerconfigurationServiceImpl extends BaseServiceImpl<SwaggerconfigurationMapper, Swaggerconfiguration> implements SwaggerconfigurationService {
    @Override
    protected PropertyWrapper<Swaggerconfiguration> makeSearchWrapper(Map<String, Object> condition) {
        return createWrapperUtil(condition)
                        .eq("basepackagename")
                                .eq("groupname")
                    .getWrapper();
    }
}
