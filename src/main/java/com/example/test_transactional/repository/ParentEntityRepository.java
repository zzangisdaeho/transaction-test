package com.example.test_transactional.repository;

import com.example.test_transactional.entity.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentEntityRepository extends JpaRepository<ParentEntity, Long> {
}
