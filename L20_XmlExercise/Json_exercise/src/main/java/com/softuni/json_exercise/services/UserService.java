package com.softuni.json_exercise.services;

import com.softuni.json_exercise.domain.dtos.users.UsersWithSoldProductsDto;
import com.softuni.json_exercise.domain.dtos.users.wrappers.UsersWithProductsWrapperDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UsersWithSoldProductsDto> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException, JAXBException;

    UsersWithProductsWrapperDto usersAndProducts() throws IOException;
}
