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

    public Goods(long id, String sku, String name, String image, long amount, String comment,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Goods(String sku, String name, String image, long amount, String comment) {
        this.id = IdGenerator.nextIdentity();
        this.sku = sku;
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
