package com.orders.dao;

import com.orders.dao.entity.OrderEntity;

public interface OrderDao {

    public OrderEntity save(OrderEntity order);
}
