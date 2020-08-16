/*
 * FileName: TxMapper
 * Author:   Akeung
 * Date:     2020/8/16
 */
package com.ak.demo.order.mapper;

import com.ak.demo.order.entity.TxInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author Akeung
 * 2020/8/16
 */
public interface TxMapper extends BaseMapper<TxInfo> {
    Boolean exists(String xid);
}