package com.users.dao;

import com.users.dao.entity.UserEntity;
import com.users.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity findByMobileOrEmail(String value) {
        return userRepository.findByEmailOrMobile(value);
    }

    @Override
    public UserEntity save(UserEntity user) {
       return userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
