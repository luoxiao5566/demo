package com.thoughtworks.mall.demo.domain.service;

import com.thoughtworks.mall.demo.domain.model.Order;
import com.thoughtworks.mall.demo.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDomainService {

    private final OrderRepository orderRepository;

    public List<Order> findAllByOrderId(Long orderId) {
        List<Order> orders = orderRepository.findByTransaction(orderId);
        log.info("orders size: {}", orders.size());
        return orders;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> saveAll(List<Order> orders) {
        return orderRepository.saveAll(orders);
    }

    @Transactional
    public void deleteAll() {
        orderRepository.deleteAll();
    }

}
