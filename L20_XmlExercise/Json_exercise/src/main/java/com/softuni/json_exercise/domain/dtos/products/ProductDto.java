package com.softuni.json_exercise.domain.dtos.products;

import com.softuni.json_exercise.domain.dtos.categories.CategoryDto;
import com.softuni.json_exercise.domain.dtos.users.UserDto;
import com.softuni.json_exercise.domain.entities.Category;
import com.softuni.json_exercise.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    private String name;

    private BigDecimal price;

    private UserDto buyer;

    private UserDto seller;

    private Set<CategoryDto> categories;

    public ProductInRangeWithNoBuyerDto toProductInRangeWithNoBuyerDto() {
        return new ProductInRangeWithNoBuyerDto(name, price, seller.getFullName());
    }


}
