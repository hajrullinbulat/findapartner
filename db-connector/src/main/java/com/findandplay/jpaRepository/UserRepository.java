package com.findandplay.jpaRepository;

import com.findandplay.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByMsisdn(String msisdn);
}

