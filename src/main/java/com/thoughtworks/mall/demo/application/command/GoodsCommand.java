package com.thoughtworks.mall.demo.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCommand {

    @NotBlank(message = "sku should not be blank")
    private String sku;

    @NotBlank(message = "name should not be blank")
    private String name;

    private String image;

    @NotNull(message = "name should not be null")
    private Long amount;

    private String comment;

}
