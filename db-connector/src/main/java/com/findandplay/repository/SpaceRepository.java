package com.findandplay.repository;

import com.findandplay.entity.SpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRepository extends JpaRepository<SpaceEntity, Long> {
}
