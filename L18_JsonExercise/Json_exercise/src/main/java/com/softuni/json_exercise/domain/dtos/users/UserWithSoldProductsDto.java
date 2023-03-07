package com.softuni.json_exercise.domain.dtos.users;

import com.softuni.json_exercise.domain.dtos.products.ProductSoldDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithSoldProductsDto {

    private String firstName;

    private String lastName;

    private List<ProductSoldDto> boughtProducts;

}
