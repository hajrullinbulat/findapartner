package com.findandplay.repository;

import com.findandplay.entity.AdvertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<AdvertEntity, Long> {
}
