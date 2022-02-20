package com.thoughtworks.mall.demo.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Goods {

    private Long id;

    private String sku;

    private String name;

    private String image;

    private Long amount;

    private String comment;

}
