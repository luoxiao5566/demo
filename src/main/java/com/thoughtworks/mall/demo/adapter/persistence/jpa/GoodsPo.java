package com.thoughtworks.mall.demo.adapter.persistence.jpa;

import com.thoughtworks.mall.demo.domain.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "goods")
@NoArgsConstructor
@AllArgsConstructor
public class GoodsPo extends AbstractEntity {

    private String sku;

    private String name;

    private String image;

    private Long amount;

    private String comment;

}
