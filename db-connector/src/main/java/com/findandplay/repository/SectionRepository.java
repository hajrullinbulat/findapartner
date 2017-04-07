package com.findandplay.repository;

import com.findandplay.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<UserEntity, Long> {
}
