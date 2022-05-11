package com.example.test_transactional.repository;

import com.example.test_transactional.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {

}
