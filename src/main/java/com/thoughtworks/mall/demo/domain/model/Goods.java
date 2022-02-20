package com.thoughtworks.mall.demo.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Goods {

    private Long id;

    private String sku;

    private String name;

    private String image;

    private Long amount;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
