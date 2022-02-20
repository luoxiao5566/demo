package com.thoughtworks.mall.demo.adapter.rest;

import com.thoughtworks.mall.demo.application.command.GoodsCommand;
import com.thoughtworks.mall.demo.application.response.GoodsInResponse;
import com.thoughtworks.mall.demo.application.service.GoodsApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Goods")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/open-api", produces = "application/json;charset=UTF-8")
public class OpenApiGoodsController {

    private final GoodsApplicationService applicationService;

    @ApiOperation(value = "添加商品")
    @PostMapping("/goods")
    public void addGoods(@RequestBody @Valid GoodsCommand goodsCommand) {
        applicationService.addGoods(goodsCommand);
    }

    @ApiOperation(value = "获取商品详情")
    @GetMapping("/goods")
    public GoodsInResponse getGoods() {
        return applicationService.getGoods();
    }

}
