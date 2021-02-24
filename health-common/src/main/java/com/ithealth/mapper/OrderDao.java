package com.ithealth.mapper;


import com.ithealth.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface OrderDao {

    /**
     * 查询用户是否已经预约
     * @param order
     * @return
     */
    List<Order> isRepeatOrder(Order order);

    /**
     * 保存预约表
     * @param order
     */
    void saveOrderInfo(Order order);

    /**
     * 预约成功后查询详情
     * @param id
     * @return
     */
    Map doOrderSuccess(Integer id);
}
