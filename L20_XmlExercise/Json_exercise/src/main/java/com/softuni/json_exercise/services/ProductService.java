package com.softuni.json_exercise.services;

import com.softuni.json_exercise.domain.dtos.products.ProductInRangeWithNoBuyerDto;
import com.softuni.json_exercise.domain.entities.Product;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductInRangeWithNoBuyerDto> findAllByPriceBetweenAndBuyerIsNullOOrderByPrice(BigDecimal low, BigDecimal high) throws IOException, JAXBException;
}
