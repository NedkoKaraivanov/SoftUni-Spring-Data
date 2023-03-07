package com.softuni.json_exercise;

import com.softuni.json_exercise.services.CategoryService;
import com.softuni.json_exercise.services.ProductService;
import com.softuni.json_exercise.services.SeedService;
import com.softuni.json_exercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class CommandRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;

    private final CategoryService categoryService;
     private final UserService userService;

    @Autowired
    public CommandRunner(SeedService seedService, ProductService productService, CategoryService categoryService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        this.seedService.seedAll();

//        this.productService.findAllByPriceBetweenAndBuyerIsNullOOrderByPrice(
//                BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

//        this.userService.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName();

        this.categoryService.getCategorySummary();
//        this.userService.usersAndProducts();
    }
}
