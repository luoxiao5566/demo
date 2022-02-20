package com.thoughtworks.mall.demo.domain.repository;

import com.thoughtworks.mall.demo.domain.model.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order, Long> {

    List<Order> findByOrderId(Long orderId);

}
