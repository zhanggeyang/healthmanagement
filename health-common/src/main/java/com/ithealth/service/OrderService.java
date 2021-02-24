package com.ithealth.service;

import com.ithealth.entity.Result;

import java.util.Map;

public interface OrderService {
    /**
     * 处理用户预约的方法
     * @param map
     * @return
     */
    Result doOrder(Map map) throws Exception;

    /**
     * 查询预约详情
     * @param id
     * @return
     */
    Map doOrderSuccess(Integer id);
}
