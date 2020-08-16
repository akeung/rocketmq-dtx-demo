package com.ak.demo.order.mapper;

import com.ak.demo.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface OrderMapper extends BaseMapper<Order> {
    void create(Order order);

    Order get(@Param("id") Long id);
}
