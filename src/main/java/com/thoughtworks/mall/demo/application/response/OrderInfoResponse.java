package com.thoughtworks.mall.demo.application.response;

import com.thoughtworks.mall.demo.domain.model.Goods;
import com.thoughtworks.mall.demo.domain.model.Order;
import com.thoughtworks.mall.demo.domain.model.vo.AddressInfo;
import com.thoughtworks.mall.demo.domain.model.vo.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderInfoResponse {

    private String goodsSku;

    private String name;

    private String image;

    private String comment;

    private Long count;

    private Long amount;

    private Long totalPrice;

    private AddressInfo address;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static OrderInfoResponse from(Order order, Goods goods) {
        return new OrderInfoResponse(order.getSku(), goods.getName(), goods.getImage(),
                goods.getComment(), order.getCount(), order.getAmount(), order.getTotal(), order.getAddress(),
                order.getStatus(), order.getCreatedAt(), order.getUpdatedAt());
    }

}
