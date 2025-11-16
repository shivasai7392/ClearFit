package com.practice.clearfit.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDto {
    private String username;
    private String emailId;
    private String password;
    private String confirmPassword;
}
