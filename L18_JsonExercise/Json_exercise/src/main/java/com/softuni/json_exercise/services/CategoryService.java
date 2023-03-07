package com.softuni.json_exercise.services;

import com.softuni.json_exercise.domain.dtos.categories.CategoryProductsSummaryDto;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<CategoryProductsSummaryDto> getCategorySummary() throws IOException;
}
