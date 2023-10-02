package com.users.dto;

import lombok.Data;

@Data
public class CreateUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;

}
