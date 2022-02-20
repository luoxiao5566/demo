package com.thoughtworks.mall.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mall.demo.application.command.GoodsCommand;
import com.thoughtworks.mall.demo.application.command.OrderCommand;
import com.thoughtworks.mall.demo.application.command.OrderInfoCommand;
import com.thoughtworks.mall.demo.domain.model.Goods;
import com.thoughtworks.mall.demo.domain.model.Order;
import com.thoughtworks.mall.demo.domain.model.vo.AddressInfo;
import com.thoughtworks.mall.demo.domain.service.GoodsDomainService;
import com.thoughtworks.mall.demo.domain.service.OrderDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GoodsDomainService goodsDomainService;

    @Autowired
    OrderDomainService orderDomainService;

    @BeforeEach
    public void setUp() {
        goodsDomainService.deleteAll();
        orderDomainService.deleteAll();
    }

    @Test
    public void should_add_order() throws Exception {

        GoodsCommand goodsCommand = GoodsCommand.builder()
                .sku("个").name("苹果").image("https://www.cxx.com")
                .amount(3000L).comment("好吃的苹果")
                .build();

        String goodsJsonString = objectMapper.writeValueAsString(goodsCommand);
        mockMvc.perform(post("/open-api/goods").content(goodsJsonString).contentType(MediaType.APPLICATION_JSON));
        List<Goods> goodsList = goodsDomainService.findAll();

        List<OrderInfoCommand> orderInfoCommandList = new ArrayList<>();

        AddressInfo addressInfo = AddressInfo.builder().address("四川成都").name("老王").phone("15866668888").build();

        OrderInfoCommand orderInfoCommand = OrderInfoCommand.builder().goodsId(goodsList.get(0).getId()).goodsSku("个").count(5L).amount(300L)
                .totalPrice(1500L).address(addressInfo).build();

        orderInfoCommandList.add(orderInfoCommand);

        OrderCommand orderCommand = OrderCommand.builder().orderInfoCommandList(orderInfoCommandList).build();

        String orderJsonString = objectMapper.writeValueAsString(orderCommand);
        mockMvc.perform(post("/open-api/order").content(orderJsonString).contentType(MediaType.APPLICATION_JSON));
        List<Order> orders = orderDomainService.findAll();
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(goodsList.get(0).getId(), orders.get(0).getGoods());
        assertEquals("个", orders.get(0).getSku());
        assertEquals(5, orders.get(0).getCount());
        assertEquals(300, orders.get(0).getAmount());
        assertEquals(1500, orders.get(0).getTotal());
        assertEquals("四川成都", orders.get(0).getAddress().getAddress());
        assertEquals("老王", orders.get(0).getAddress().getName());
        assertEquals("15866668888", orders.get(0).getAddress().getPhone());
    }

    @Test
    public void should_get_order_info() throws Exception {

        GoodsCommand goodsCommand = GoodsCommand.builder()
                .sku("个").name("苹果").image("https://www.cxx.com")
                .amount(300L).comment("好吃的苹果")
                .build();

        String goodsJsonString = objectMapper.writeValueAsString(goodsCommand);
        mockMvc.perform(post("/open-api/goods").content(goodsJsonString).contentType(MediaType.APPLICATION_JSON));
        List<Goods> goodsList = goodsDomainService.findAll();

        List<OrderInfoCommand> orderInfoCommandList = new ArrayList<>();

        AddressInfo addressInfo = AddressInfo.builder().address("四川成都").name("老王").phone("15866668888").build();

        OrderInfoCommand orderInfoCommand = OrderInfoCommand.builder().goodsId(goodsList.get(0).getId()).goodsSku("个").count(5L).amount(300L)
                .totalPrice(1500L).address(addressInfo).build();

        orderInfoCommandList.add(orderInfoCommand);

        OrderCommand orderCommand = OrderCommand.builder().orderInfoCommandList(orderInfoCommandList).build();

        String orderJsonString = objectMapper.writeValueAsString(orderCommand);
        mockMvc.perform(post("/open-api/order").content(orderJsonString).contentType(MediaType.APPLICATION_JSON));

        List<Order> orders = orderDomainService.findAll();
        Long transaction = orders.get(0).getTransaction();

        mockMvc.perform(get("/open-api/order/" + transaction))
                .andExpect(jsonPath("$.orderInfoResponseList", hasSize(1)))
                .andExpect(jsonPath("$.orderInfoResponseList[0].goodsSku", is("个")))
                .andExpect(jsonPath("$.orderInfoResponseList[0].name", is("苹果")))
                .andExpect(jsonPath("$.orderInfoResponseList[0].image", is("https://www.cxx.com")))
                .andExpect(jsonPath("$.orderInfoResponseList[0].amount", is(300)))
                .andExpect(jsonPath("$.orderInfoResponseList[0].comment", is("好吃的苹果")))
                .andExpect(jsonPath("$.orderInfoResponseList[0].count", is(5)))
                .andExpect(jsonPath("$.orderInfoResponseList[0].totalPrice", is(1500)))
                .andExpect(jsonPath("$.orderInfoResponseList[0].address.address", is("四川成都")))
                .andExpect(jsonPath("$.orderInfoResponseList[0].address.name", is("老王")))
                .andExpect(jsonPath("$.orderInfoResponseList[0].address.phone", is("15866668888")));
    }

}
