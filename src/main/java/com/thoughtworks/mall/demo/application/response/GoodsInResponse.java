package com.thoughtworks.mall.demo.application.response;

import com.thoughtworks.mall.demo.domain.model.Goods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoodsInResponse {

    private List<Goods> goodsList;

    public static GoodsInResponse from(List<Goods> goodsList) {
        return new GoodsInResponse(goodsList);
    }

}
