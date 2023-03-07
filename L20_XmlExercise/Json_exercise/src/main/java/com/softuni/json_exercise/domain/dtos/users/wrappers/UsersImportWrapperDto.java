package com.softuni.json_exercise.domain.dtos.users.wrappers;

import com.softuni.json_exercise.domain.dtos.users.UserImportDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


//<users> - RootElement
//<user first-name="Eugene" last-name="Stewart" age="65"/>
//<user first-name="Fred" last-name="Allen" age="57"/>
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportWrapperDto {

    @XmlElement(name = "user")
    private List<UserImportDto> users;
}
