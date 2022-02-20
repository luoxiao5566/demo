package com.thoughtworks.mall.demo.application.service;

import com.thoughtworks.mall.demo.application.command.GoodsCommand;
import com.thoughtworks.mall.demo.application.response.GoodsInResponse;
import com.thoughtworks.mall.demo.domain.model.Goods;
import com.thoughtworks.mall.demo.domain.service.GoodsDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsApplicationService {

    private final GoodsDomainService goodsDomainService;

    @Transactional
    public void addGoods(GoodsCommand goodsCommand) {
        Goods goods = new Goods(goodsCommand.getSku(), goodsCommand.getName(), goodsCommand.getImage(),
                goodsCommand.getAmount(), goodsCommand.getComment());
        goodsDomainService.save(goods);
    }

    @Transactional(readOnly = true)
    public GoodsInResponse getGoods() {
        return GoodsInResponse.from(goodsDomainService.findAll());
    }

}
