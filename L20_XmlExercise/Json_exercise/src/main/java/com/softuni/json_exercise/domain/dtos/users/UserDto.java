package com.softuni.json_exercise.domain.dtos.users;

import com.softuni.json_exercise.domain.dtos.products.ProductBasicInfoDto;
import com.softuni.json_exercise.domain.dtos.products.ProductDto;
import com.softuni.json_exercise.domain.dtos.products.ProductsSoldWithCountDto;
import com.softuni.json_exercise.domain.entities.Product;
import com.softuni.json_exercise.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstName;

    private String lastName;

    private Integer age;

    private Set<ProductDto> sellingProducts;

    private Set<ProductDto> boughtProducts;

    private Set<UserDto> friends;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public UserWithProductsDto toUserWithProductsDto() {
        return new UserWithProductsDto(firstName, lastName, age, toProductsSoldWithCountDto());
    }

    public ProductsSoldWithCountDto toProductsSoldWithCountDto() {
        return new ProductsSoldWithCountDto(sellingProducts
                .stream()
                .map(this::toProductBasicInfoDto)
                .collect(Collectors.toList()));
    }

    public ProductBasicInfoDto toProductBasicInfoDto(ProductDto productDto) {
        return new ProductBasicInfoDto(productDto.getName(), productDto.getPrice());
    }
}
