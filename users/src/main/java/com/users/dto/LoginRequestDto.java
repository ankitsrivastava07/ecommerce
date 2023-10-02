package com.users.dto;
import lombok.Data;
@Data
public class LoginRequestDto {

    private String emailOrMobile;
    private String password;
}
