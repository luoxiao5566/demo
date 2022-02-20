package com.thoughtworks.mall.demo.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderResponse {

    List<OrderInfoResponse> orderInfoResponseList;

    public static OrderResponse from(List<OrderInfoResponse> orderInfoResponseList) {
        return new OrderResponse(orderInfoResponseList);
    }
}
