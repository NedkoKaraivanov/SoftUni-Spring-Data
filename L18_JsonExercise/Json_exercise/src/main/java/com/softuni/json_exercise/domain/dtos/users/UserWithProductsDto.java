package com.softuni.json_exercise.domain.dtos.users;

import com.softuni.json_exercise.domain.dtos.products.ProductBasicInfoDto;
import com.softuni.json_exercise.domain.dtos.products.ProductsSoldWithCountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithProductsDto {

    private String firstName;

    private String lastName;

    private Integer age;

    private ProductsSoldWithCountDto products;
}
