package com.findandplay.repository;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.findandplay.entity.UserEntity;

public interface UserRepository extends EntityGraphJpaRepository<UserEntity, Long> {
    UserEntity findUserById(Long id, EntityGraph entityGraph);

    UserEntity findByMsisdn(String msisdn, EntityGraph graph);

    UserEntity findByMsisdn(String msisdn);
}

