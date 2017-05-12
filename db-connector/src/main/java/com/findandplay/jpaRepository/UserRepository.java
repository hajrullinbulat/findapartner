package com.findandplay.jpaRepository;

import com.findandplay.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(value = "UserEntity.roles", type = EntityGraph.EntityGraphType.LOAD)
    UserEntity findByMsisdn(String msisdn);
}

