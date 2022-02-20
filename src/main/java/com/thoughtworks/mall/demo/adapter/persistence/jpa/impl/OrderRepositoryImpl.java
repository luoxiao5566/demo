package com.thoughtworks.mall.demo.adapter.persistence.jpa.impl;

import com.thoughtworks.mall.demo.adapter.persistence.DefaultBaseRepository;
import com.thoughtworks.mall.demo.adapter.persistence.jpa.OrderPo;
import com.thoughtworks.mall.demo.domain.model.Order;
import com.thoughtworks.mall.demo.domain.repository.OrderRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.matching;

@Repository
public class OrderRepositoryImpl extends DefaultBaseRepository<Order, OrderPo, Long> implements OrderRepository {

    public OrderRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Order> findByTransaction(Long orderId) {

        OrderPo orderPo = new OrderPo();
        orderPo.setTransaction(orderId);
        return jpaRepository.findAll(Example.of(orderPo, matching().withMatcher("transaction", exact())))
                .stream().map(this::convertEntityToDomain).collect(Collectors.toList());
    }
}
