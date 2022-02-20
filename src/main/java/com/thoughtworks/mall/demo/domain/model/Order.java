package com.thoughtworks.mall.demo.domain.model;

import com.thoughtworks.mall.demo.domain.model.vo.AddressInfo;
import com.thoughtworks.mall.demo.domain.model.vo.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Order {

    private Long id;

    private Long orderId;

    private String goodsSku;

    private Long count;

    private Long amount;

    private Long totalPrice;

    private AddressInfo address;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
