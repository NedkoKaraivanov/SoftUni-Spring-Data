package com.softuni.json_exercise.domain.dtos.categories.wrappers;

import com.softuni.json_exercise.domain.dtos.categories.CategoryProductsSummaryDto;
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
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesProductSummaryWrapperDto {

    @XmlElement(name = "category")
    private List<CategoryProductsSummaryDto> categories;
}
