package com.users.dao.repository;

import com.users.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select * from users where email=?1 or mobile=?1", nativeQuery = true)
    UserEntity findByEmailOrMobile(String value);

    UserEntity findByEmail(String email);
}
