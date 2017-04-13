package com.findandplay.jpaRepository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.findandplay.entity.CheckedAdvertByUserEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CheckedAdverts extends EntityGraphJpaRepository<CheckedAdvertByUserEntity, Long> {
}
