package com.users.dao;

import com.users.dao.entity.UserEntity;

public interface UserDao {

    UserEntity findByMobileOrEmail(String value);

    UserEntity save(UserEntity user);

    UserEntity findByEmail(String email);
}
