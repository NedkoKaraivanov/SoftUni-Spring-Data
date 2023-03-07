package com.softuni.json_exercise.services;

import com.softuni.json_exercise.constants.Utils;
import com.softuni.json_exercise.domain.dtos.categories.CategoryProductsSummaryDto;
import com.softuni.json_exercise.domain.dtos.categories.wrappers.CategoriesProductSummaryWrapperDto;
import com.softuni.json_exercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;


import static com.softuni.json_exercise.constants.Paths.CATEGORIES_BY_PRODUCTS_JSON_PATH;
import static com.softuni.json_exercise.constants.Paths.CATEGORIES_BY_PRODUCTS_XML_PATH;
import static com.softuni.json_exercise.constants.Utils.writeJsonIntoFile;
import static com.softuni.json_exercise.constants.Utils.writeXmlIntoFile;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryProductsSummaryDto> getCategorySummary() throws IOException, JAXBException {

        final List<CategoryProductsSummaryDto> categories =
                this.categoryRepository
                        .getCategorySummary()
                        .orElseThrow(NoSuchElementException::new);

        CategoriesProductSummaryWrapperDto categoriesWrapper =
                new CategoriesProductSummaryWrapperDto(categories);

        Utils.writeXmlIntoFile(categoriesWrapper, CATEGORIES_BY_PRODUCTS_XML_PATH);
        Utils.writeJsonIntoFile(categories, CATEGORIES_BY_PRODUCTS_JSON_PATH);

        return categories;
    }
}
