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

    private Long transaction;

    private Long goods;

    private String sku;

    private Long count;

    private Long amount;

    private Long total;

    private AddressInfo address;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Order(long transaction, Long goods, String sku, Long count, Long amount, Long total,
                 AddressInfo address, LocalDateTime now) {
        this.id = IdGenerator.nextIdentity();
        this.transaction = transaction;
        this.goods = goods;
        this.sku = sku;
        this.count = count;
        this.amount = amount;
        this.total = total;
        this.address = address;
        this.status = OrderStatus.CREATED;
        this.createdAt = now;
        this.updatedAt = now;
    }
}
