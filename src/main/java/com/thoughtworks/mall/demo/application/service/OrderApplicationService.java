package com.thoughtworks.mall.demo.application.service;

import com.thoughtworks.mall.demo.application.command.OrderCommand;
import com.thoughtworks.mall.demo.application.response.OrderInfoResponse;
import com.thoughtworks.mall.demo.application.response.OrderResponse;
import com.thoughtworks.mall.demo.domain.model.Goods;
import com.thoughtworks.mall.demo.domain.model.IdGenerator;
import com.thoughtworks.mall.demo.domain.model.Order;
import com.thoughtworks.mall.demo.domain.service.GoodsDomainService;
import com.thoughtworks.mall.demo.domain.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderApplicationService {

    private final OrderDomainService orderDomainService;

    private final GoodsDomainService goodsDomainService;


    @Transactional
    public void addOrder(OrderCommand orderCommand) {
        long orderId = IdGenerator.nextIdentity();
        LocalDateTime now = LocalDateTime.now();

        List<Order> orders = orderCommand.getOrderInfoCommandList().stream().map(orderInfoCommand -> {
            Goods goods = goodsDomainService.findById(orderInfoCommand.getGoodsId());
            return new Order(orderId, goods.getId(), goods.getSku(), orderInfoCommand.getCount(),
                    orderInfoCommand.getAmount(), orderInfoCommand.getTotalPrice(), orderInfoCommand.getAddress(), now);
        }).collect(Collectors.toList());

        orderDomainService.saveAll(orders);
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long orderId) {

        List<Order> orders = orderDomainService.findAllByOrderId(orderId);
        log.info("transaction:{},goods:{}", orders.get(0).getTransaction(), orders.get(0).getGoods());
        List<Long> goodsIds = orders.stream().map(Order::getGoods).collect(Collectors.toList());

        log.info("goodsIds size:{}", goodsIds.size());
        Map<Long, Goods> goodsMap = goodsDomainService.findByIds(goodsIds).stream()
                .collect(Collectors.toMap(Goods::getId, item -> item, (v1, v2) -> v2));

        List<OrderInfoResponse> orderInfoResponseList = orders.stream().map(order -> {
            Goods goods = Optional.ofNullable(goodsMap.get(order.getGoods())).orElse(new Goods());
            return OrderInfoResponse.from(order, goods);
        }).collect(Collectors.toList());

        return OrderResponse.from(orderInfoResponseList);
    }

}
