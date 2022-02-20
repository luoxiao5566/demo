package com.thoughtworks.mall.demo.adapter.rest;

import com.thoughtworks.mall.demo.application.command.OrderCommand;
import com.thoughtworks.mall.demo.application.response.OrderResponse;
import com.thoughtworks.mall.demo.application.service.OrderApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Order")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/open-api", produces = "application/json;charset=UTF-8")
public class OpenApiOrderController {

    private final OrderApplicationService applicationService;

    @ApiOperation(value = "添加订单")
    @PostMapping("/order")
    public void addGoods(@RequestBody @Valid OrderCommand orderCommand) {
        applicationService.addOrder(orderCommand);
    }

    @ApiOperation(value = "获取订单详情")
    @GetMapping("/order/{orderId}")
    public OrderResponse getGoods(@PathVariable(name = "orderId") Long orderId) {
        return applicationService.getOrder(orderId);
    }

}
