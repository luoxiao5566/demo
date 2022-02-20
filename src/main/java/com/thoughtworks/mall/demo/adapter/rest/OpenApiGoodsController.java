package com.thoughtworks.mall.demo.adapter.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Goods")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/open-api", produces = "application/json;charset=UTF-8")
public class OpenApiGoodsController {


}
