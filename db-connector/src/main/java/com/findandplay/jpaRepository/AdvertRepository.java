package com.findandplay.jpaRepository;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.findandplay.entity.AdvertEntity;
import com.findandplay.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AdvertRepository extends EntityGraphJpaRepository<AdvertEntity, Long> {
    AdvertEntity findAdvertById(Long id, EntityGraph entityGraph);

    List<AdvertEntity> findAdvertsByAuthor(UserEntity userEntity, EntityGraph entityGraph);
}
