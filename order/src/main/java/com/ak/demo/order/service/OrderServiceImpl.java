package com.ak.demo.order.service;

import com.ak.demo.entities.TxAccountMessage;
import com.ak.demo.order.entity.Order;
import com.ak.demo.order.feign.AccountClient;
import com.ak.demo.order.feign.StorageClient;
import com.ak.demo.order.mapper.OrderMapper;
import com.ak.demo.utils.IdGenerator;
import com.ak.demo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Autowired
    private AccountClient accountClient;
    @Autowired
    private StorageClient storageClient;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void create(Order order) {
        // 产生事务ID
        String xid = UUID.randomUUID().toString().replace("-", "");

        //对事务相关数据进行封装，并转成 json 字符串
        TxAccountMessage txAccountMessage = new TxAccountMessage(order.getUserId(), order.getMoney(), xid);
        String json = JsonUtil.to(txAccountMessage);

        //json字符串封装到 Spring Message 对象
        Message<String> message = MessageBuilder.withPayload(json).build();

        //发送事务消息
        rocketMQTemplate.sendMessageInTransaction("order-topic:account",message,order);
        log.info("事务消息已发送");
    }

    /**
     *本地事务，执行订单保存
     *这个方法在事务监听器中调用
     * @param order
     */
    @Override
    @Transactional
    public void doCreate(Order order) {
        log.info("执行本地事务，保存订单");
        Long nextId = IdGenerator.getInstance().nextId();
        order.setId(nextId);
        orderMapper.create(order);

        //todo 1.模拟事务异常
//        if (Math.random() < 0.5) {
//            throw new RuntimeException("模拟异常");
//        }

        log.info("订单已保存！ 事务日志已保存");
    }


    @Override
    public Order get(Long id) {
        return orderMapper.get(id);
    }
}