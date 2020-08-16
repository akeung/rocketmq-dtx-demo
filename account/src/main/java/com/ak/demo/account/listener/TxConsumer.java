/*
 * FileName: TxConsumer
 * Author:   Akeung
 * Date:     2020/8/17
 */
package com.ak.demo.account.listener;

import com.ak.demo.account.service.AccountService;
import com.ak.demo.entities.TxAccountMessage;
import com.ak.demo.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Akeung
 * 2020/8/17
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "account-consumer-group",topic = "order-topic",selectorExpression = "account")
public class TxConsumer implements RocketMQListener<String> {

    @Autowired
    private AccountService accountService;

    @Override
    public void onMessage(String message) {
        TxAccountMessage txAccountMessage = JsonUtil.from(message, new TypeReference<TxAccountMessage>() {});
        log.info("收到消息： "+txAccountMessage);
        accountService.decrease(txAccountMessage.getUserId(),txAccountMessage.getMoney());
    }
}
