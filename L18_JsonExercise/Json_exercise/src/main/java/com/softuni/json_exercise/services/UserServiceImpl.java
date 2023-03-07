package com.softuni.json_exercise.services;

import com.softuni.json_exercise.domain.dtos.users.UserDto;
import com.softuni.json_exercise.domain.dtos.users.UserWithProductsDto;
import com.softuni.json_exercise.domain.dtos.users.UserWithSoldProductsDto;
import com.softuni.json_exercise.domain.dtos.users.UsersWithProductsWrapperDto;
import com.softuni.json_exercise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.softuni.json_exercise.constants.Paths.USERS_AND_PRODUCTS_JSON_PATH;
import static com.softuni.json_exercise.constants.Paths.USERS_WITH_SOLD_PRODUCTS_JSON_PATH;
import static com.softuni.json_exercise.constants.Utils.MODEL_MAPPER;
import static com.softuni.json_exercise.constants.Utils.writeJsonIntoFile;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserWithSoldProductsDto> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException {
       final List<UserWithSoldProductsDto> usersWithSoldProductsDtoList = this.userRepository.
                findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> MODEL_MAPPER.map(user, UserWithSoldProductsDto.class))
                .collect(Collectors.toList());

       writeJsonIntoFile(usersWithSoldProductsDtoList, USERS_WITH_SOLD_PRODUCTS_JSON_PATH);

        return usersWithSoldProductsDtoList;

    }

    @Override
    public UsersWithProductsWrapperDto usersAndProducts() throws IOException {

        final List<UserWithProductsDto> usersAndProducts = this.userRepository.
                findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> MODEL_MAPPER.map(user, UserDto.class))
                .map(UserDto::toUserWithProductsDto)
                .collect(Collectors.toList());

       final UsersWithProductsWrapperDto usersWithProductsWrapperDto = new UsersWithProductsWrapperDto(usersAndProducts);

        writeJsonIntoFile(usersWithProductsWrapperDto, USERS_AND_PRODUCTS_JSON_PATH);

        return usersWithProductsWrapperDto;

    }
}
