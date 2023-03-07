package com.softuni.json_exercise.services;

import com.softuni.json_exercise.domain.dtos.users.UserWithSoldProductsDto;
import com.softuni.json_exercise.domain.dtos.users.UsersWithProductsWrapperDto;
import com.softuni.json_exercise.domain.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDto> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException;

    UsersWithProductsWrapperDto usersAndProducts() throws IOException;
}
