package com.softuni.gamestore.domain.dtos;

import java.util.regex.Pattern;

import static com.softuni.gamestore.constants.Validations.*;

public class UserRegisterDTO {
    private String email;

    private String password;

    private String confirmPassword;

    private String fullName;

    public UserRegisterDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }

    private void validate() {
        final boolean isEmailValid = Pattern.matches(EMAIL_PATTERN, this.email);
        if (!isEmailValid) {
            throw new IllegalArgumentException(EMAIL_NOT_VALID_MESSAGE);
        }

        final boolean isPasswordValid = Pattern.matches(PASSWORD_PATTERN, this.password);
        if (!isPasswordValid) {
            throw new IllegalArgumentException(USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE);
        }

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException(PASSWORDS_MISS_MATCH_MESSAGE);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String successfulRegisterFormat() {
        return fullName + " was registered";
    }
}