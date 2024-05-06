package com.nuc.cloud.service;

import com.nuc.cloud.entities.Order;

public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);
}
