package com.softuni.json_exercise.services;

import com.softuni.json_exercise.constants.Utils;
import com.softuni.json_exercise.domain.dtos.categories.CategoryProductsSummaryDto;
import com.softuni.json_exercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.softuni.json_exercise.constants.Paths.CATEGORIES_BY_PRODUCTS_JSON_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryProductsSummaryDto> getCategorySummary() throws IOException {

        final List<CategoryProductsSummaryDto> categoryProductsSummaryDtos =
                this.categoryRepository
                        .getCategorySummary()
                        .orElseThrow(NoSuchElementException::new);

        Utils.writeJsonIntoFile(categoryProductsSummaryDtos, CATEGORIES_BY_PRODUCTS_JSON_PATH);

        return categoryProductsSummaryDtos;
    }
}
