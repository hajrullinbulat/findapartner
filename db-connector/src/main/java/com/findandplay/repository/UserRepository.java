package com.findandplay.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.findandplay.entity.UserEntity;

public interface UserRepository extends EntityGraphJpaRepository<UserEntity, Long> {
}
