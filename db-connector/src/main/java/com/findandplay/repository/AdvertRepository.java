package com.findandplay.repository;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.findandplay.entity.AdvertEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdvertRepository extends EntityGraphJpaRepository<AdvertEntity, Long> {
    AdvertEntity findAdvertById(Long id, EntityGraph entityGraph);
}
