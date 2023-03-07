package com.softuni.json_exercise.domain.dtos.products;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsSoldWithCountDto {

    private Integer count;

    private List<ProductBasicInfoDto> products;

    public ProductsSoldWithCountDto(List<ProductBasicInfoDto> products) {
        this.products = products;
        this.count = products.size();
    }
}
