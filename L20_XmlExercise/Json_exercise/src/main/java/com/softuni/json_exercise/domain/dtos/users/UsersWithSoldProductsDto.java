package com.softuni.json_exercise.domain.dtos.users;

import com.softuni.json_exercise.domain.dtos.products.ProductSoldDto;
import com.softuni.json_exercise.domain.dtos.products.wrappers.ProductsSoldWrapperDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersWithSoldProductsDto {

    private String firstName;

    private String lastName;

    private List<ProductSoldDto> boughtProducts;

    public static List<UserWithSoldProductsXmlDto> toUsersWithSoldProductsDto(List<UsersWithSoldProductsDto> input) {
        return input.stream()
                .map(user -> new UserWithSoldProductsXmlDto(user.getFirstName(),
                        user.getLastName(),
                        new ProductsSoldWrapperDto(user.getBoughtProducts())))
                .collect(Collectors.toList());
    }
}
