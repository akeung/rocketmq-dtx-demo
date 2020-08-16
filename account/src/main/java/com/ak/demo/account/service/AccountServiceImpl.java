package com.ak.demo.account.service;

import com.ak.demo.account.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Transactional
    @Override
    public void decrease(Long userId, BigDecimal money) {
        accountMapper.decrease(userId,money);

        //todo 2.模拟事务异常
        if (Math.random() < 0.5) {
            throw new RuntimeException("模拟异常");
        }
    }
}