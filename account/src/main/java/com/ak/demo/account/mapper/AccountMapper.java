package com.ak.demo.account.mapper;

import com.ak.demo.account.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;

public interface AccountMapper extends BaseMapper<Account> {
    void decrease(Long userId, BigDecimal money);
}