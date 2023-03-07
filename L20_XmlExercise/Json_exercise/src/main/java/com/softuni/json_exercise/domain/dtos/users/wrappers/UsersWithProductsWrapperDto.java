package com.softuni.json_exercise.domain.dtos.users.wrappers;

import com.softuni.json_exercise.domain.dtos.users.UserWithProductsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsersWithProductsWrapperDto {

    private Integer usersCount;

    private List<UserWithProductsDto> users;

    public UsersWithProductsWrapperDto(List<UserWithProductsDto> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}
