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
        return orderRepository.findByOrderId(orderId);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteAll() {
        orderRepository.deleteAll();
    }

}
