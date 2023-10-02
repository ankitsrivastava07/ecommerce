package com.users.service;

import com.users.convertor.ConvertDtoToEntity;
import com.users.dao.UserDao;
import com.users.dao.entity.UserEntity;
import com.users.dto.ApiResponse;
import com.users.dto.CreateUserDto;
import com.users.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import static com.users.constant.UserConstant.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public ApiResponse login(LoginRequestDto loginRequestDto) {
        UserEntity user = userDao
                .findByMobileOrEmail(loginRequestDto
                        .getEmailOrMobile());

        if (user == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("accountErr", "Account does not exist");
            return ApiResponse.builder()
                    .setMsg("Account does not exist")
                    .setStatus(Boolean.FALSE)
                    .setError(error)
                    .build();
        }

        if (new BCryptPasswordEncoder().matches(loginRequestDto.getPassword(), user.getPassword())) {
            return ApiResponse.builder()
                    .setStatus(TRUE)
                    .setMsg(LOGIN_SUCCESS_DEFAULT_MSG)
                    .build();
        } else {
            return ApiResponse.builder()
                    .setStatus(FALSE)
                    .setMsg(INVALID_CREDENTIAL_DEFAULT_MSG)
                    .build();
        }
    }

    @Override
    public ApiResponse createUser(CreateUserDto createUserDto) {

        createUserDto.setPassword(new BCryptPasswordEncoder()
                .encode(createUserDto.getPassword()));

        UserEntity user = ConvertDtoToEntity
                .dtoToEntityConvertor(createUserDto, UserEntity.class);

        Object value;
        if ((value = userDao.findByEmail(createUserDto.getEmail())) != null) {
            return ApiResponse
                    .builder()
                    .setStatus(FALSE)
                    .setMsg(ACCOUNT_ALREADY_EXIST_DEFAULT_MSG)
                    .build();
        }

        user = userDao.save(user);
        return ApiResponse
                .builder()
                .setMsg(ACCOUNT_CREATED_SUCCESSFULLY_DEFAULT_MSG)
                .setStatus(TRUE)
                .build();
    }
}
