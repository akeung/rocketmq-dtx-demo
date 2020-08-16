package com.ak.demo.account.service;

import java.math.BigDecimal;

public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}