package com.thoughtworks.mall.demo.application.command;

import com.thoughtworks.mall.demo.domain.model.vo.AddressInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoCommand {

    private Long goodsId;

    private String goodsSku;

    private Long count;

    private Long amount;

    private Long totalPrice;

    private AddressInfo address;

}
