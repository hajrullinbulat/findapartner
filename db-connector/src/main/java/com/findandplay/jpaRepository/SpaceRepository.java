package com.findandplay.jpaRepository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.findandplay.entity.SpaceEntity;

public interface SpaceRepository extends EntityGraphJpaRepository<SpaceEntity, Long> {
}
