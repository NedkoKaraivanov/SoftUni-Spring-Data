package com.softuni.json_exercise.services;

import com.softuni.json_exercise.domain.dtos.products.ProductDto;
import com.softuni.json_exercise.domain.dtos.products.ProductInRangeWithNoBuyerDto;
import com.softuni.json_exercise.domain.entities.Product;
import com.softuni.json_exercise.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.softuni.json_exercise.constants.Paths.PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH;
import static com.softuni.json_exercise.constants.Utils.MODEL_MAPPER;
import static com.softuni.json_exercise.constants.Utils.writeJsonIntoFile;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductInRangeWithNoBuyerDto> findAllByPriceBetweenAndBuyerIsNullOOrderByPrice(BigDecimal low, BigDecimal high) throws IOException {
        final List<ProductInRangeWithNoBuyerDto> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                .orElseThrow(NoSuchElementException::new)
                .stream().map(product -> MODEL_MAPPER.map(product, ProductDto.class))
                .map(ProductDto::toProductInRangeWithNoBuyerDto)
                .collect(Collectors.toList());

        writeJsonIntoFile(products, PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH);

        return products;
    }
}
