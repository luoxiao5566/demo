package com.thoughtworks.mall.demo.adapter.persistence.jpa;

import com.thoughtworks.mall.demo.domain.model.AbstractEntity;
import com.thoughtworks.mall.demo.domain.model.vo.AddressInfo;
import com.thoughtworks.mall.demo.domain.model.vo.OrderStatus;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class OrderPo extends AbstractEntity {

    private Long transaction;

    private Long goods;

    private String sku;

    private Long count;

    private Long amount;

    private Long total;

    @Type(type = "jsonb")
    @Column(name = "address", columnDefinition = "jsonb")
    private AddressInfo address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
