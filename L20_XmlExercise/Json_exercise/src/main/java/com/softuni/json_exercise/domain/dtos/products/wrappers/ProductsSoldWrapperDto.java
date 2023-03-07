package com.softuni.json_exercise.domain.dtos.products.wrappers;

import com.softuni.json_exercise.domain.dtos.products.ProductSoldDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldWrapperDto {

    @XmlElement(name = "product")
    private List<ProductSoldDto> boughtProducts;
}
