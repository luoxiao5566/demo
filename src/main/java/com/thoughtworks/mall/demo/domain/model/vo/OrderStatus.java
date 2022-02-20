package com.thoughtworks.mall.demo.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    CREATED("创建"),
    DELIVERY("已发货"),
    FINISH("已完成");

    private final String value;

}
