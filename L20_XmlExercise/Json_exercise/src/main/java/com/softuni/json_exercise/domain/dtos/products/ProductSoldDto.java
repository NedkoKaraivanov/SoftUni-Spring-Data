package com.softuni.json_exercise.domain.dtos.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSoldDto {

    @XmlElement
    private String name;

    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "buyer-first-name")
    private String buyerFirstName;

    @XmlElement(name = "buyer-last-name")
    private String buyerLastName;
}
