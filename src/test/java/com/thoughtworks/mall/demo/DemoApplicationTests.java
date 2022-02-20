package com.thoughtworks.mall.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mall.demo.application.command.GoodsCommand;
import com.thoughtworks.mall.demo.domain.model.Goods;
import com.thoughtworks.mall.demo.domain.service.GoodsDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
class DemoApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GoodsDomainService goodsDomainService;

    @BeforeEach
    public void setUp() {
        goodsDomainService.deleteAll();
    }

    @Test
    public void should_add_goods() throws Exception {

        GoodsCommand goodsCommand = GoodsCommand.builder()
                .sku("个").name("苹果").image("https://www.cxx.com")
                .amount(3000L).comment("好吃的苹果")
                .build();

        String jsonString = objectMapper.writeValueAsString(goodsCommand);
        mockMvc.perform(post("/open-api/goods").content(jsonString).contentType(MediaType.APPLICATION_JSON));
        List<Goods> goodsList = goodsDomainService.findAll();
        assertNotNull(goodsList);
        assertEquals(1,goodsList.size());
        assertEquals("个",goodsList.get(0).getSku());
        assertEquals("苹果",goodsList.get(0).getName());
        assertEquals("https://www.cxx.com",goodsList.get(0).getImage());
        assertEquals(3000,goodsList.get(0).getAmount());
        assertEquals("好吃的苹果",goodsList.get(0).getComment());
    }

    @Test
    public void should_get_all_goods() throws Exception {

        GoodsCommand goodsCommand = GoodsCommand.builder()
                .sku("个").name("苹果").image("https://www.cxx.com")
                .amount(3000L).comment("好吃的苹果")
                .build();

        String jsonString = objectMapper.writeValueAsString(goodsCommand);
        mockMvc.perform(post("/open-api/goods").content(jsonString).contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/open-api/goods"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].sku", is("个")))
                .andExpect(jsonPath("$[0].name", is("苹果")))
                .andExpect(jsonPath("$[0].image", is("https://www.cxx.com")))
                .andExpect(jsonPath("$[0].amount", is(3000)))
                .andExpect(jsonPath("$[0].comment", is("好吃的苹果")));

    }

}
