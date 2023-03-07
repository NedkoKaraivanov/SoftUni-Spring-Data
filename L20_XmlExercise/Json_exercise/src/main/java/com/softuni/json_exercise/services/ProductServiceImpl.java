package com.softuni.json_exercise.services;

import com.softuni.json_exercise.domain.dtos.products.ProductDto;
import com.softuni.json_exercise.domain.dtos.products.ProductInRangeWithNoBuyerDto;
import com.softuni.json_exercise.domain.dtos.products.wrappers.ProductsInRangeWithNoBuyerWrapperDto;
import com.softuni.json_exercise.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.softuni.json_exercise.constants.Paths.PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH;
import static com.softuni.json_exercise.constants.Paths.PRODUCTS_WITH_NO_BUYERS_IN_RANGE_XML_PATH;
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
    public List<ProductInRangeWithNoBuyerDto> findAllByPriceBetweenAndBuyerIsNullOOrderByPrice(BigDecimal low, BigDecimal high) throws IOException, JAXBException {
        final List<ProductInRangeWithNoBuyerDto> products =
                this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                .orElseThrow(NoSuchElementException::new)
                .stream().map(product -> MODEL_MAPPER.map(product, ProductDto.class))
                .map(ProductDto::toProductInRangeWithNoBuyerDto)
                .collect(Collectors.toList());

        writeJsonIntoFile(products, PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH); //json

        final ProductsInRangeWithNoBuyerWrapperDto productsWrapper = new ProductsInRangeWithNoBuyerWrapperDto(products); //xml

        final File file = PRODUCTS_WITH_NO_BUYERS_IN_RANGE_XML_PATH.toFile();

        final JAXBContext context = JAXBContext.newInstance(ProductsInRangeWithNoBuyerWrapperDto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(productsWrapper, file);

        return products;
    }
}
