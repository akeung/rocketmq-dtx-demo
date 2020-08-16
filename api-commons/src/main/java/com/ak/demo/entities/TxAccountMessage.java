/*
 * FileName: TxAccountMessage
 * Author:   Akeung
 * Date:     2020/8/16
 */
package com.ak.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Akeung
 * 2020/8/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxAccountMessage {
    Long userId;
    BigDecimal money;
    String xid;
}
