package com.jnetdata.simple.core.service.impl;


import com.jnetdata.simple.core.service.BaseService;

/**
 * Created by Administrator on 2018/8/28.
 * @author
 */
public class BaseServiceImpl <M extends org.thenicesys.mybatis.BaseMapper<T>, T> extends org.thenicesys.mybatis.impl.BaseServiceImpl<M, T> implements BaseService<T> {

}
