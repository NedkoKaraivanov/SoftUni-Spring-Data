package com.softuni.json_exercise.services;

import com.softuni.json_exercise.domain.dtos.users.UserDto;
import com.softuni.json_exercise.domain.dtos.users.UserWithProductsDto;
import com.softuni.json_exercise.domain.dtos.users.UsersWithSoldProductsDto;
import com.softuni.json_exercise.domain.dtos.users.wrappers.UsersWithProductsWrapperDto;
import com.softuni.json_exercise.domain.dtos.users.wrappers.UsersWithSoldProductsWrapperDto;
import com.softuni.json_exercise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.softuni.json_exercise.constants.Paths.*;
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
    public List<UsersWithSoldProductsDto> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException, JAXBException {
       final List<UsersWithSoldProductsDto> usersWithSoldProductsDtoList = this.userRepository.
                findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> MODEL_MAPPER.map(user, UsersWithSoldProductsDto.class))
                .collect(Collectors.toList());

        final UsersWithSoldProductsWrapperDto usersWithSoldProductsWrapperDto =
               new UsersWithSoldProductsWrapperDto().ofListOfUsersWithSoldProductsDto(usersWithSoldProductsDtoList);

        writeJsonIntoFile(usersWithSoldProductsDtoList, USERS_WITH_SOLD_PRODUCTS_JSON_PATH);

        final File file = USERS_WITH_SOLD_PRODUCTS_XML_PATH.toFile();

        final JAXBContext context = JAXBContext.newInstance(UsersWithSoldProductsWrapperDto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(usersWithSoldProductsWrapperDto, file);

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
