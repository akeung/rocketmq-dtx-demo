package com.ak.demo.order.controller;

import com.ak.demo.order.entity.Order;
import com.ak.demo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;


    /*
    用户用这个路径进行访问：
    http://localhost:8081/create?userId=1&productId=1&count=10&money=100
     */
    @GetMapping("/create")
    public String create(Order order) {
        log.info("创建订单");
        orderService.create(order);
        return "创建订单成功";
    }

    @GetMapping("/get/{id}")
    public Order get(@PathVariable Long id) {
        return  orderService.get(id);
    }
}
