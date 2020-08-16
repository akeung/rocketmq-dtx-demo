/*
 * FileName: TxInfo
 * Author:   Akeung
 * Date:     2020/8/16
 */
package com.ak.demo.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Akeung
 * 2020/8/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxInfo {
    private String xid;
    private long created;
    private int status;
}
